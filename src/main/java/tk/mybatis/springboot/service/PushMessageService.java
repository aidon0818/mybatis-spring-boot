package tk.mybatis.springboot.service;

import tk.mybatis.springboot.dto.OrderDTO;

/**
 * Created by Dong_Liu
 * date：2017/9/15
 */
public interface PushMessageService {
    /**
     * 订单状态变更消息
     *
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
