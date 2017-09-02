package tk.mybatis.springboot.mapper;

import tk.mybatis.springboot.model.Test;
import tk.mybatis.springboot.util.MyMapper;

import java.util.List;

public interface TestMapper extends MyMapper<Test> {
    public List<Test> getTestList(Test test);
}
