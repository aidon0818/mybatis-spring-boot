package tk.mybatis.springboot.util;

import tk.mybatis.springboot.dto.CartDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @Auther: ld
 * @Date: 2019/3/27 17:14
 * @Param ${tags}
 * @Description:
 */
public class FactoryTest {
    Map<Class, Function<String, String>> serviceMap = new HashMap<Class, Function<String, String>>();

    public String upayTally(Object reqObj) {
        Function<String, String> function = serviceMap.get(reqObj.getClass());
//        String rtnObj = (String) function.apply(params);
        return "";
    }

    public static void main(String[] args) {
//        FactoryTest factoryTest=new FactoryTest();
        CartDTO cartDTO = new CartDTO("1", 0);
        Object o = cartDTO;
        System.out.println(o.getClass());
//        factoryTest.upayTally(o);
    }
}
