package tk.mybatis.springboot.mapper;

import java.util.List;

import tk.mybatis.springboot.model.OrderDetail;
import tk.mybatis.springboot.util.MyMapper;

/**
 * @Author dong_liu
 * @Date 2017/9/13 15:34
 * 订单描述
 */
public interface OrderDetailMapper extends MyMapper<OrderDetail> {
    List<OrderDetail> findByOrderid(OrderDetail orderDetail);
}