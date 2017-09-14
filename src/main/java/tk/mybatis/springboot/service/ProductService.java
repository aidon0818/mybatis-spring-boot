package tk.mybatis.springboot.service;

import java.util.List;
import tk.mybatis.springboot.dto.CartDTO;
import tk.mybatis.springboot.model.ProductInfo;

/**
 * Created by Dong_Liu
 * date：2017/9/14
 */
public interface ProductService {
    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    List<ProductInfo> findAll(ProductInfo productInfo);

    int save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    int onSale(String productId);

    //下架
    int offSale(String productId);
}
