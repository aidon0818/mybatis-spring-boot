package tk.mybatis.springboot.service;

import com.github.pagehelper.PageHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.mapper.ProductCategoryMapper;
import tk.mybatis.springboot.model.ProductCategory;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    public List<ProductCategory> getAll(ProductCategory productCategory) {
        if (productCategory.getPage() != null && productCategory.getRows() != null) {
            PageHelper.startPage(productCategory.getPage(), productCategory.getRows());
        }
        List<ProductCategory> list= (List<ProductCategory>) productCategoryMapper.selectAll();
        return list;
    }

    public ProductCategory getById(Integer id) {
        return productCategoryMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        productCategoryMapper.deleteByPrimaryKey(id);
    }

    public ProductCategory save(ProductCategory productCategory) {
        if (productCategory.getCategoryId() != null) {
            productCategoryMapper.updateByPrimaryKey(productCategory);
        } else {
            productCategoryMapper.insert(productCategory);
        }
        return productCategory;
    }

    public List<ProductCategory> findByCategoryTypeIn(List<Integer> typeList) {
        List<ProductCategory> productCategoryList = productCategoryMapper.findByCategoryTypeIn(typeList);
        return productCategoryList;
    }
}
