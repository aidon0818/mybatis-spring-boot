package tk.mybatis.springboot.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import tk.mybatis.springboot.dto.OrderDTO;

/**
 * Created by Dong_Liu
 * date：2017/9/29
 */
public interface PayService {
    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
