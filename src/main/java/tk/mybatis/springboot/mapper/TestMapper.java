package tk.mybatis.springboot.mapper;

import tk.mybatis.springboot.model.Test;

import java.util.List;

public interface TestMapper {
    public List<Test> getTestList(Test test);
}
