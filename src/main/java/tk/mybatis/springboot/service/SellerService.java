package tk.mybatis.springboot.service;

import tk.mybatis.springboot.model.SellerInfo;

/**
 * Created by Dong_Liu
 * date：2017/9/29
 */
public interface SellerService {
    /**
     * 通过openid查询卖家端信息
     *
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
