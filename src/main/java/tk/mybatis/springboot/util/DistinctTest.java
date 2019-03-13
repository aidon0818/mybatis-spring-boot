package tk.mybatis.springboot.util;

import tk.mybatis.springboot.dto.CartDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: ld
 * @Date: 2019/3/13 14:40
 * @Param ${tags}
 * @Description:
 */
public class DistinctTest {
    public static void main(String[] args) {
        test2();

    }

    private static void test1() {
        List<String> list = Arrays.asList("AA", "BB", "CC", "BB", "CC", "AA", "AA");
        long l = list.stream().distinct().count();
        System.out.println("No. of distinct elements:"+l);
        String output = list.stream().distinct().collect(Collectors.joining(","));
        System.out.println(output);
    }
    private static void test2() {
        List<CartDTO> ls=new ArrayList<>();
        ls.add(new CartDTO("a",1));
        ls.add(new CartDTO("a1",1));
        ls.add(new CartDTO("a2",1));
        ls.add(new CartDTO("a1",1));
        ls.add(new CartDTO("a",2));
        long l = ls.stream().distinct().count();
        System.out.println("No. of distinct books:"+l);
        ls.stream().distinct().forEach(b -> System.out.println(b.getProductId()+ "," + b.getProductQuantity()));
    }

}
