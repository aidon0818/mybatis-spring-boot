package tk.mybatis.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.mapper.SellerInfoMapper;
import tk.mybatis.springboot.model.SellerInfo;
import tk.mybatis.springboot.service.SellerService;

/**
 * Created by Dong_Liu
 * date：2017/9/29
 */
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoMapper repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
