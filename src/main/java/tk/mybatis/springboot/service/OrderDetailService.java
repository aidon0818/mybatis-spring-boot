package tk.mybatis.springboot.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.mapper.OrderDetailMapper;
import tk.mybatis.springboot.model.OrderDetail;

import java.util.List;

/**
 * Created by Dong_Liu
 * date：2017/9/13
 */
@Service
public class OrderDetailService {
    @Autowired
    OrderDetailMapper orderDetailMapper;

    public List<OrderDetail> getAll(OrderDetail orderDetail) {
        if (orderDetail.getPage() != null && orderDetail.getRows() != null) {
            PageHelper.startPage(orderDetail.getPage(), orderDetail.getRows());
        }
        List<OrderDetail> list = (List<OrderDetail>) orderDetailMapper.selectAll();
        return list;
    }

    public OrderDetail getById(Integer id) {
        return orderDetailMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        orderDetailMapper.deleteByPrimaryKey(id);
    }

    public OrderDetail save(OrderDetail orderDetail) {
//        if (orderDetail.getDetailId() != null) {
//            orderDetailMapper.updateByPrimaryKey(orderDetail);
//        } else {
//            orderDetailMapper.insert(orderDetail);
//        }
        orderDetailMapper.insert(orderDetail);
        return orderDetail;
    }

    public List<OrderDetail> findByOrderid(OrderDetail orderDetail) {
        if (orderDetail.getPage() != null && orderDetail.getRows() != null) {
            PageHelper.startPage(orderDetail.getPage(), orderDetail.getRows());
        }
        List<OrderDetail> list = (List<OrderDetail>) orderDetailMapper.findByOrderid(orderDetail);
        return list;
    }
}
