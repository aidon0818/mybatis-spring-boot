package tk.mybatis.springboot.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.springboot.model.AjaxMessage;
import tk.mybatis.springboot.model.ProductCategory;
import tk.mybatis.springboot.service.ProductCategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping
    public AjaxMessage getAll(ProductCategory productCategory) {
        Map<String, Object> processResult = new HashMap<String, Object>();
        List<ProductCategory> productCategoryList = productCategoryService.getAll(productCategory);
        processResult.put("productCategoryList", productCategoryList);
        return AjaxMessage.succeed(processResult);
    }

}
