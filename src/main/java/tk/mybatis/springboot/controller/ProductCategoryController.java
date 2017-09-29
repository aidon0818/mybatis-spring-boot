package tk.mybatis.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.springboot.dto.ProductDTO;
import tk.mybatis.springboot.dto.ProductInfoDTO;
import tk.mybatis.springboot.dto.ResultDTO;
import tk.mybatis.springboot.model.ProductCategory;
import tk.mybatis.springboot.model.ProductInfo;
import tk.mybatis.springboot.service.ProductCategoryService;
import tk.mybatis.springboot.service.ProductInfoService;
import tk.mybatis.springboot.util.ResultDTOUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/buyer/product")

public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductInfoService productInfoService;


    /**
     * 买家商品
     */
    @GetMapping("/list")
    public ResultDTO getAll(ProductCategory productCategory) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductStatus(0);
        List<ProductDTO> productDTOList = null;
        try {
            List<ProductInfo> productInfoList = productInfoService.getAll(productInfo);
            //精简方法(java8, lambda)
            List<Integer> categoryTypeList = productInfoList.stream()
                    .map(e -> e.getCategoryType()).collect(Collectors.toList());
            List<ProductCategory> productCategoryList = productCategoryService.
                    findByCategoryTypeIn(categoryTypeList);
            productDTOList = new ArrayList<>();
            setProductDTO(productInfoList, productCategoryList, productDTOList);
            return ResultDTOUtil.success(productDTOList);
        } catch (Exception e) {
            return ResultDTOUtil.error(1, "返回信息错误！");
        }
    }

    /**
    * @Author dong_liu 
    * @Date  2017/9/13 14:42
    */
    private void setProductDTO(List<ProductInfo> productInfoList, List<ProductCategory> productCategoryList, List<ProductDTO> productDTOList) {
        for (ProductCategory productCategoryNew : productCategoryList) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setCategoryType(productCategoryNew.getCategoryType());
            productDTO.setCategoryName(productCategoryNew.getCategoryName());
            List<ProductInfoDTO> productInfoDTOList = new ArrayList<>();
            for (ProductInfo productInfoNew : productInfoList) {
                if (productInfoNew.getCategoryType().equals(productCategoryNew.getCategoryType())) {
                    ProductInfoDTO productInfoDTO = new ProductInfoDTO();
                    BeanUtils.copyProperties(productInfoNew, productInfoDTO);
                    productInfoDTOList.add(productInfoDTO);
                }
            }
            productDTO.setProductInfoDTOList(productInfoDTOList);
            productDTOList.add(productDTO);
        }
    }
}
