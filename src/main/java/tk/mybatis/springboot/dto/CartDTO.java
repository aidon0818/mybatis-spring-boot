package tk.mybatis.springboot.dto;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 购物车
 * Created by 廖师兄
 * 2017-06-11 19:37
 */

public class CartDTO {

    /** 商品Id. */
    private String productId;

    /** 数量. */
    private Integer productQuantity;

    private Date createTime;

    private BigDecimal amt;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "productId='" + productId + '\'' +
                ", productQuantity=" + productQuantity +
                ", createTime=" + createTime +
                ", amt=" + amt +
                '}';
    }
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        final CartDTO book = (CartDTO) obj;
        if (this == book) {
            return true;
        } else {
            return (this.productId.equals(book.productId) && this.productQuantity == book.productQuantity);
        }
    }
    @Override
    public int hashCode() {
        int hashno = 7;
        hashno = 13 * hashno + (productId == null ? 0 : productId.hashCode());
        return hashno;
    }

}
