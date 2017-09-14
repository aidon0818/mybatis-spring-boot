package tk.mybatis.springboot.service;


import java.util.List;

import tk.mybatis.springboot.dto.OrderDTO;
import tk.mybatis.springboot.model.OrderMaster;


/**
 * Created by Dong_Liu
 * date：2017/9/14
 */
public interface OrderService {
    /**
     * 创建订单.
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单.
     */
    OrderDTO findOne(String orderId);

    /**
     * 查询订单列表.
     */
    List<OrderDTO> findList(OrderMaster orderMaster);

    /**
     * 取消订单.
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完结订单.
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单.
     */
    OrderDTO paid(OrderDTO orderDTO);

}
