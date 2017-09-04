package tk.mybatis.springboot.util;


import tk.mybatis.springboot.enumUtil.ResultEnum;

public class EnumTest {
    public static void main(String[] args) {
        Integer code = -1;
        for (ResultEnum a:ResultEnum.values()){
            System.out.println(a.getCode());
            System.out.println(a.getMsg());
        }
    }
}
