package tk.mybatis.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProductDTO {
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoDTO> productInfoDTOList;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public List<ProductInfoDTO> getProductInfoDTOList() {
        return productInfoDTOList;
    }

    public void setProductInfoDTOList(List<ProductInfoDTO> productInfoDTOList) {
        this.productInfoDTOList = productInfoDTOList;
    }
}
