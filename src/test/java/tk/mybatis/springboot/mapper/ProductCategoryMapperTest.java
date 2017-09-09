package tk.mybatis.springboot.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.springboot.model.ProductCategory;
import tk.mybatis.springboot.service.ProductCategoryService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = productCategoryService.getById(1);
    }
}