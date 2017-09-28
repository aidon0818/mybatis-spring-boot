package tk.mybatis.springboot.util;

import org.springframework.beans.BeanUtils;
import tk.mybatis.springboot.dto.OrderDTO;
import tk.mybatis.springboot.model.OrderMaster;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dong_Liu
 * date：2017/9/28
 */
public class OrderMaster2OrderDTOConverter {
    public static OrderDTO convert(OrderMaster orderMaster) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
