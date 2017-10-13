package tk.mybatis.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Dong_Liu
 * date：2017/10/13
 */
@RestController
@RequestMapping("/aop")
public class AopTestController {


    @RequestMapping("/testBeforeService")
    public String testBeforeService(String key, String value) {

        return "key=" + key + "  value=" + value;
    }

    @RequestMapping("/testAfterReturning")
    public String testAfterReturning(String key) {

        return "key=: " + key;
    }

    @RequestMapping("/testAfterReturning01")
    public Integer testAfterReturning01(Integer key) {

        return key;
    }
    @RequestMapping("/testAroundService")
    public String testAroundService(String key){

        return "环绕通知："+key;
    }
}
