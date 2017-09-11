package tk.mybatis.springboot.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.springboot.mapper.ProductCategoryMapper;
import tk.mybatis.springboot.model.ProductCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceTest {
    @Autowired
    private ProductCategoryService productCategoryService;
    @Test
    public void getAll() throws Exception {
        ProductCategory productCategory=new ProductCategory();
        List<ProductCategory> productCategoryList = productCategoryService.getAll(productCategory);
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void getById() throws Exception {
        ProductCategory productCategory=this.productCategoryService.getById(1);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void deleteById() throws Exception {

    }

    @Test
    //加上Transactional事物注释后，添加数据库不会出现新条目
    @Transactional
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("男生菜谱");
        productCategory.setCategoryType(1);
        ProductCategory result = productCategoryService.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(Arrays.asList(1));
        Assert.assertNotEquals(0, productCategoryList.size());
    }

}