package tk.mybatis.springboot.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.mapper.OrderMasterMapper;
import tk.mybatis.springboot.model.OrderMaster;

import java.util.List;

/**
 * Created by Dong_Liu
 * date：2017/9/13
 */
@Service
public class OrderMasterService {
    @Autowired
    private OrderMasterMapper orderMasterMapper;

    public List<OrderMaster> getAll(OrderMaster orderMaster) {
        if (orderMaster.getPage() != null && orderMaster.getRows() != null) {
            PageHelper.startPage(orderMaster.getPage(), orderMaster.getRows());
        }
        List<OrderMaster> list = (List<OrderMaster>) orderMasterMapper.selectAll();
        return list;
    }


    public OrderMaster getById(Integer id) {
        return orderMasterMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        orderMasterMapper.deleteByPrimaryKey(id);
    }

    public OrderMaster save(OrderMaster orderMaster) {
        if (orderMaster.getOrderId() != null) {
            orderMasterMapper.updateByPrimaryKey(orderMaster);
        } else {
            orderMasterMapper.insert(orderMaster);
        }
        return orderMaster;
    }

    public List<OrderMaster> findByBuyOpenId(OrderMaster orderMaster) {
        if (orderMaster.getPage() != null && orderMaster.getRows() != null) {
            PageHelper.startPage(orderMaster.getPage(), orderMaster.getRows());
        }
        return orderMasterMapper.findByBuyOpenId(orderMaster);
    }
}
