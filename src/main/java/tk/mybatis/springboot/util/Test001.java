package tk.mybatis.springboot.util;

import com.alibaba.druid.util.StringUtils;

import java.math.BigDecimal;

/**
 * Created by Dong_Liu
 * date：2017/9/14
 */
public class Test001 {
    public static void main(String[] args) {
        boolean a = StringUtils.equals("a", "a");
        System.out.println(a);
        Integer b = StringUtils.stringToInteger("ads123");
        System.out.println(b);
        //取ds和a之间的字符
        String c = StringUtils.subString("dskeabcedeh", "ds", "a");
        System.out.println(c);
    }
}
