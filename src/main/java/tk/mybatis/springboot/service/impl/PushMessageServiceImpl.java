package tk.mybatis.springboot.service.impl;

import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.dto.OrderDTO;
import tk.mybatis.springboot.service.PushMessageService;

/**
 * Created by Dong_Liu
 * date：2017/9/15
 */
@Service
public class PushMessageServiceImpl implements PushMessageService {
    @Autowired
    private WxMpService wxMpService;

    @Override
    public void orderStatus(OrderDTO orderDTO1) {

    }
}
