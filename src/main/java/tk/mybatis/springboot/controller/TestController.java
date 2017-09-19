package tk.mybatis.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.springboot.model.Test;
import tk.mybatis.springboot.service.TestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")

public class TestController {
   @Value("${com.name}")
   private String testName;

    @RequestMapping
    public String getTestList() {

        return testName;
    }
}
