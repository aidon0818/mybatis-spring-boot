package tk.mybatis.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.dto.CartDTO;
import tk.mybatis.springboot.enumUtil.ProductStatusEnum;
import tk.mybatis.springboot.mapper.ProductInfoMapper;
import tk.mybatis.springboot.model.ProductInfo;
import tk.mybatis.springboot.service.ProductService;

import java.util.List;

/**
 * Created by Dong_Liu
 * date：2017/9/14
 */

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoMapper.selectByPrimaryKey(productId);
    }

    //获取在架商品
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoMapper.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findAll(ProductInfo productInfo) {
        if (productInfo.getPage() != null && productInfo.getRows() != null) {
            PageHelper.startPage(productInfo.getPage(), productInfo.getRows());
        }
        return productInfoMapper.selectAll();
    }

    @Override
    public int save(ProductInfo productInfo) {
        int status = productInfoMapper.insert(productInfo);
        return status;
    }

    //加库存
    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(cartDTO.getProductId());
            if (productInfo == null) {
                return;
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            if (productInfo.getProductId() != null) {
                productInfoMapper.updateByPrimaryKey(productInfo);
            } else {
                productInfoMapper.insert(productInfo);
            }
        }
    }

    //减库存
    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(cartDTO.getProductId());
            if (productInfo == null) {
                return;
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                return;
            }
            productInfo.setProductStock(result);
            if (productInfo.getProductId() != null) {
                productInfoMapper.updateByPrimaryKey(productInfo);
            } else {
                productInfoMapper.insert(productInfo);
            }
        }
    }

    //上架
    @Override
    public int onSale(String productId) {
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(productId);
        if (null != productInfo && productInfo.getProductStatus() != ProductStatusEnum.UP.getCode()) {
            //更新
            productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        }
        int status = productInfoMapper.updateByPrimaryKey(productInfo);
        return status;

    }

    //下架
    @Override
    public int offSale(String productId) {
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(productId);
        if (null != productInfo && productInfo.getProductStatus() != ProductStatusEnum.DOWN.getCode()) {
            //更新
            productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        }
        int status = productInfoMapper.updateByPrimaryKey(productInfo);
        return status;
    }
}
