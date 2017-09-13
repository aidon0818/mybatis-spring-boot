package tk.mybatis.springboot.service;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.springboot.model.ProductInfo;

import java.math.BigDecimal;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceTest {
    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void getAll() throws Exception {
        ProductInfo productInfo=new ProductInfo();
        List<ProductInfo> productInfoPage = productInfoService.getAll(productInfo);
//        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0, productInfoPage.size());

    }

    @Test
    public void getById() throws Exception {
        ProductInfo productInfo = productInfoService.getById("2d2f9eec78304134bb2e07b40c7df0e7");
        Assert.assertEquals("2d2f9eec78304134bb2e07b40c7df0e7", productInfo.getProductId());
    }

    @Test
    public void deleteById() throws Exception {
    }

    @Test
    public void save() throws Exception {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId(uuid);
        productInfo.setProductName("烧烤");
        productInfo.setProductPrice(new BigDecimal(12.0));
        productInfo.setProductStock(10);
        productInfo.setCategoryType(0);
    ProductInfo result = productInfoService.save(productInfo);
        Assert.assertNotNull(result);
}

    @Test
    public void findByProductStatus() throws Exception {
        productInfoService.findByProductStatus(1);
    }

}