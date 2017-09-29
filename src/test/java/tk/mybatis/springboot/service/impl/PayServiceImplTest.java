package tk.mybatis.springboot.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.springboot.dto.OrderDTO;
import tk.mybatis.springboot.service.OrderService;
import tk.mybatis.springboot.service.PayService;

import static org.junit.Assert.*;

/**
 * Created by Dong_Liu
 * date：2017/9/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {
    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1506587937313296063");
        payService.create(orderDTO);
    }

//    @Test
//    public void notify() throws Exception {
//    }

    @Test
    public void refund() throws Exception {
    }

}