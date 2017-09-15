package tk.mybatis.springboot.service;

import com.github.pagehelper.PageHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.springboot.model.OrderDetail;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Dong_Liu
 * date：2017/9/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailServiceTest {
    @Autowired
    private OrderDetailService orderDetailService;

    @Test
    public void getAll() throws Exception {
    }

    @Test
    public void getById() throws Exception {
    }

    @Test
    public void deleteById() throws Exception {
    }

    @Test
    public void save() throws Exception {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567810");
        orderDetail.setOrderId("1234567");
        orderDetail.setProductIcon("http://xxxx.jpg");
        orderDetail.setProductId("11111112");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(2.2));
        orderDetail.setProductQuantity(3);
        OrderDetail result = orderDetailService.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderid() throws Exception {
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setOrderId("1234567");
        if (orderDetail.getPage() != null && orderDetail.getRows() != null) {
            PageHelper.startPage(orderDetail.getPage(), orderDetail.getRows());
        }
        List<OrderDetail> list = (List<OrderDetail>) orderDetailService.findByOrderid(orderDetail);
        Assert.assertNotEquals(0,list.size());
    }

}