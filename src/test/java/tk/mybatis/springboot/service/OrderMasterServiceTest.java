package tk.mybatis.springboot.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.springboot.model.OrderMaster;

/**
 * Created by Dong_Liu
 * date：2017/9/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterServiceTest {
    @Autowired
    private OrderMasterService orderMasterService;

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
    }

    @Test
    public void findByBuyOpenId() throws Exception {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerOpenid("1101110");
        orderMaster.setPage(5);
        orderMaster.setRows(5);
        List<OrderMaster> orderMasterList = (List<OrderMaster>) orderMasterService.findByBuyOpenId(orderMaster);
        Assert.assertNotEquals(0, orderMasterList.size());
    }
}