package tk.mybatis.springboot.mapper;

import tk.mybatis.springboot.model.SellerInfo;
import tk.mybatis.springboot.util.MyMapper;

public interface SellerInfoMapper extends MyMapper<SellerInfo> {
    SellerInfo findByOpenid(String openid);
}