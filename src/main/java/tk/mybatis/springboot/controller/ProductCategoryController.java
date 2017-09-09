package tk.mybatis.springboot.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.springboot.model.Country;
import tk.mybatis.springboot.model.ProductCategory;
import tk.mybatis.springboot.service.ProductCategoryService;

import java.util.List;

@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping
    public ModelAndView getAll(ProductCategory productCategory) {
        ModelAndView result = new ModelAndView("index");
        List<ProductCategory> productCategoryList = productCategoryService.getAll(productCategory);
        result.addObject("pageInfo", new PageInfo<ProductCategory>(productCategoryList));
        result.addObject("queryParam", productCategory);
        result.addObject("page", productCategory.getPage());
        result.addObject("rows", productCategory.getRows());
        return result;
    }

}
