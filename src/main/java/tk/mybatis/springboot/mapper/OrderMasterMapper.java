package tk.mybatis.springboot.mapper;

import java.util.List;

import tk.mybatis.springboot.model.OrderMaster;
import tk.mybatis.springboot.util.MyMapper;

/**
 * @Author dong_liu
 * @Date 2017/9/13 15:34
 * 订单
 */
public interface OrderMasterMapper extends MyMapper<OrderMaster> {
    List<OrderMaster> findByBuyOpenId(OrderMaster orderMaster);
}