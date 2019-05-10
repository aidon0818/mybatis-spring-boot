package tk.mybatis.springboot.controller;

import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.springboot.dto.OrderDTO;
import tk.mybatis.springboot.dto.ResultDTO;
import tk.mybatis.springboot.enumUtil.ResultEnum;
import tk.mybatis.springboot.exception.SellException;
import tk.mybatis.springboot.form.OrderForm;
import tk.mybatis.springboot.model.OrderMaster;
import tk.mybatis.springboot.service.BuyerService;
import tk.mybatis.springboot.service.OrderService;
import tk.mybatis.springboot.util.OrderForm2OrderDTOConverter;
import tk.mybatis.springboot.util.ResultVOUtil;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dong_Liu
 * date：2017/9/27
 */

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;
    Logger log = LoggerFactory.getLogger(BuyerOrderController.class);

    /***
     * 创建订单
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResultDTO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    //订单详情
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResultDTO<OrderDTO> detail(@RequestParam("openid") String openid,
                                      @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public ResultDTO cancel(@RequestParam("openid") String openid,
                            @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return ResultVOUtil.success();
    }

    //订单列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultDTO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setPage(page - 1);
        orderMaster.setRows(size);
        orderMaster.setBuyerOpenid(openid);
        List<OrderDTO> orderDTOPage = orderService.findList(orderMaster);
        return ResultVOUtil.success(orderDTOPage);

    }
}
