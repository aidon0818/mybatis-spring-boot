package tk.mybatis.springboot.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.mapper.ProductCategoryMapper;
import tk.mybatis.springboot.model.ProductCategory;

import java.util.List;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    public List<ProductCategory> getAll(ProductCategory productCategory) {
        if (productCategory.getPage() != null && productCategory.getRows() != null) {
            PageHelper.startPage(productCategory.getPage(), productCategory.getRows());
        }
        return productCategoryMapper.selectAll();
    }

    public ProductCategory getById(Integer id) {
        return productCategoryMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        productCategoryMapper.deleteByPrimaryKey(id);
    }

    public void save(ProductCategory country) {
        if (country.getId() != null) {
            productCategoryMapper.updateByPrimaryKey(country);
        } else {
            productCategoryMapper.insert(country);
        }
    }
}
