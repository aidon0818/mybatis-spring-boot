package tk.mybatis.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.springboot.dto.CartDTO;
import tk.mybatis.springboot.dto.OrderDTO;
import tk.mybatis.springboot.enumUtil.OrderStatusEnum;
import tk.mybatis.springboot.enumUtil.PayStatusEnum;
import tk.mybatis.springboot.enumUtil.ResultEnum;
import tk.mybatis.springboot.exception.SellException;
import tk.mybatis.springboot.mapper.OrderDetailMapper;
import tk.mybatis.springboot.mapper.OrderMasterMapper;
import tk.mybatis.springboot.model.OrderDetail;
import tk.mybatis.springboot.model.OrderMaster;
import tk.mybatis.springboot.model.ProductInfo;
import tk.mybatis.springboot.service.OrderService;
import tk.mybatis.springboot.service.PayService;
import tk.mybatis.springboot.service.ProductService;
import tk.mybatis.springboot.service.WebSocket;
import tk.mybatis.springboot.util.KeyUtil;
import tk.mybatis.springboot.util.OrderMaster2OrderDTOConverter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dong_Liu
 * date：2017/9/14
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterMapper orderMasterMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private WebSocket webSocket;
    @Autowired
    private PayService payService;

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);
        //1. 查询商品（数量, 价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2. 计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            saveOrderDetail(orderId, orderDetail, productInfo);
        }
        //3. 写入订单数据库（orderMaster和orderDetail）
        saveOrderMaster(orderDTO, orderId, orderAmount);
        //4. 扣库存
        decreaseStock(orderDTO);
        //发送websocket消息
        webSocket.sendMessage(orderDTO.getOrderId());
        return orderDTO;
    }

    private void saveOrderDetail(String orderId, OrderDetail orderDetail, ProductInfo productInfo) {
        orderDetail.setDetailId(KeyUtil.genUniqueKey());
        orderDetail.setOrderId(orderId);
        BeanUtils.copyProperties(productInfo, orderDetail);
        orderDetailMapper.insert(orderDetail);
    }

    private void decreaseStock(OrderDTO orderDTO) {
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
    }

    private void saveOrderMaster(OrderDTO orderDTO, String orderId, BigDecimal orderAmount) {
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterMapper.insert(orderMaster);
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderId);
        if (null == orderMaster) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailMapper.findByOrderid(orderDetail);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> findList(OrderMaster orderMaster) {
        if (orderMaster.getPage() != null && orderMaster.getRows() != null) {
            PageHelper.startPage(orderMaster.getPage(), orderMaster.getRows());
        }
        List<OrderMaster> orderDTOList = orderMasterMapper.selectAll();
        List<OrderDTO> orderDTOListNew = OrderMaster2OrderDTOConverter.convert(orderDTOList);
        return orderDTOListNew;
    }

    /**
     * 取消订单.
     */
    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        if (updateOrderMaster(orderDTO, orderMaster)) {
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if (increaseStock(orderDTO)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        //如果已支付, 需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            payService.refund(orderDTO);
        }
        return orderDTO;
    }

    private boolean increaseStock(OrderDTO orderDTO) {
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
            return false;
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        return true;
    }

    private boolean updateOrderMaster(OrderDTO orderDTO, OrderMaster orderMaster) {
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        int updateStatus = orderMasterMapper.updateByPrimaryKey(orderMaster);
        if (updateStatus == 0) {
            log.error("【取消订单】更新失败, orderMaster={}", orderMaster);
            return false;
        }
        return true;
    }

    /**
     * 完结订单.
     */
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        int updateOrderMaster = this.orderMasterMapper.updateByPrimaryKey(orderMaster);
        if (0 == updateOrderMaster) {
            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //推送微信模版消息
//        pushMessageService.orderStatus(orderDTO);
        return orderDTO;
    }

    /**
     * 支付订单.
     */
    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付完成】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付完成】订单支付状态不正确, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        int updateResult = orderMasterMapper.updateByPrimaryKey(orderMaster);
        if (updateResult == 0) {
            log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
