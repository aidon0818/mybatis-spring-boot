package tk.mybatis.springboot.mapper;

import java.util.List;
import tk.mybatis.springboot.model.ProductCategory;
import tk.mybatis.springboot.util.MyMapper;

public interface ProductCategoryMapper extends MyMapper<ProductCategory> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> typeList);

}