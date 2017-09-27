package tk.mybatis.springboot.service;

import tk.mybatis.springboot.dto.OrderDTO;

/**
 * Created by Dong_Liu
 * date：2017/9/27
 */
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
