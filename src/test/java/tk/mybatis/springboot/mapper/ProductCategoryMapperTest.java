package tk.mybatis.springboot.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.springboot.model.ProductCategory;
import tk.mybatis.springboot.service.ProductCategoryService;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryService productCategoryService;

//    @Test
//    public void findAll() {
//        ProductCategory productCategory=new ProductCategory();
//        List<ProductCategory> productCategoryList = productCategoryService.getAll(productCategory);
//        Assert.assertNotEquals(0,productCategoryList.size());
//    }

}