package tk.mybatis.springboot.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.mapper.TestMapper;
import tk.mybatis.springboot.model.Test;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestMapper testMapper;

    public List<Test> getTestList(Test test) {
        //分页
        if(test.getPage()!=null && test.getRows()!=null){
            PageHelper.startPage(test.getPage(), test.getRows());
        }
        List<Test> list = testMapper.getTestList(test);
        return list;
    }
}
