package tk.mybatis.springboot.mapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.springboot.model.OrderMaster;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Dong_Liu
 * date：2017/9/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterMapperTest {
    @Autowired
    private OrderMasterMapper repository;

    private final String OPENID = "110110";

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("123456789123");
        orderMaster.setBuyerAddress("幕课网");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        int result = repository.insert(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyOpenId() throws Exception {

    }

}