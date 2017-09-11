package tk.mybatis.springboot.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.mapper.ProductInfoMapper;
import tk.mybatis.springboot.model.ProductInfo;

import java.util.List;

@Service
public class ProductInfoService {
    @Autowired
    private ProductInfoMapper productInfoMapper;

    public List<ProductInfo> getAll(ProductInfo productCategory) {
        if (productCategory.getPage() != null && productCategory.getRows() != null) {
            PageHelper.startPage(productCategory.getPage(), productCategory.getRows());
        }
        List<ProductInfo> list = (List<ProductInfo>) productInfoMapper.selectAll();
        return list;
    }

    public ProductInfo getById(String id) {
        return productInfoMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        productInfoMapper.deleteByPrimaryKey(id);
    }

    public ProductInfo save(ProductInfo productInfo) {
//        if (productInfo.getProductId() != null) {
//            productInfoMapper.updateByPrimaryKey(productInfo);
//        } else {
//            productInfoMapper.insert(productInfo);
//        }
        productInfoMapper.insert(productInfo);
        return productInfo;
    }

    public List<ProductInfo> findByProductStatus(Integer productStatus) {
        return this.productInfoMapper.findByProductStatus(productStatus);
    }
}
