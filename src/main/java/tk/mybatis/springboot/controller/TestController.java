package tk.mybatis.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.springboot.model.Test;
import tk.mybatis.springboot.service.TestService;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping
    public List<Test> getTestList() {
        Test test = new Test();
        test.setId(1);
        test.setName("123");
        List<Test> list = testService.getTestList(test);
        return list;
    }
}
