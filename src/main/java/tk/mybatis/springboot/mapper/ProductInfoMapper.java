package tk.mybatis.springboot.mapper;

import java.util.List;

import tk.mybatis.springboot.model.ProductInfo;
import tk.mybatis.springboot.util.MyMapper;

public interface ProductInfoMapper extends MyMapper<ProductInfo> {
    List<ProductInfo> findByProductStatus(Integer productStatus);
}