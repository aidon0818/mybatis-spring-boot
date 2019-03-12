package tk.mybatis.springboot.util;

import tk.mybatis.springboot.dto.CartDTO;

import java.math.BigDecimal;
import java.util.*;

/**
 * 作    者 : DongLiu
 * 日    期 : 2017/12/20 15:49
 * 描    述 :
 */
public class ArrayListTest {
    public static void main(String[] args) {
        test2();
    }

    public static void test2() {

        List<CartDTO> list1 = new ArrayList<CartDTO>();
        for (int i = 0; i < 2; i++) {
            CartDTO cartDTO = new CartDTO("A" + i, i);
            cartDTO.setAmt(new BigDecimal(i));
            cartDTO.setCreateTime(new Date());
            list1.add(cartDTO);
        }

        List<CartDTO> list2 = new ArrayList<CartDTO>();
        for (int i = 4; i < 6; i++) {
            CartDTO cartDTO = new CartDTO("A" + i, i);
            cartDTO.setAmt(new BigDecimal(i));
            cartDTO.setCreateTime(new Date());
            list2.add(cartDTO);
        }
        List<CartDTO> list3 = new ArrayList<CartDTO>();
        list3.addAll(list1);
        list3.addAll(list2);
        List<CartDTO> newList = new ArrayList<CartDTO>();
        Set<CartDTO> personSet = new TreeSet<>((o1, o2) -> o1.getProductQuantity().compareTo(o2.getProductQuantity()));
        personSet.addAll(list3);
        newList.addAll(personSet);
        System.out.println(newList.size());
        for (CartDTO c:newList) {
            System.out.println(c);
        }

    }

    public static void test3() {
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        List<String> list2 = new ArrayList<String>();
        list2.add("1");
        List<String> list3 = new ArrayList<String>();
        list3.addAll(list1);
        list3.addAll(list2);
        list3.stream().distinct();
        System.out.println(list3);
    }

    private static void test() {
        String str[] = {"asd", "1sdf", "sfe", "asd", "ddd", "sss", "ddd", "sss", "wer", "erw"};
        Set<String> set = new HashSet<String>();
        Map<String, Integer> map = new TreeMap<String, Integer>();

        for (String s : str) {
            if (set.add(s)) {
                map.put(s, 1);
            } else {
                map.put(s, map.get(s) + 1);
            }
        }

        int size = map.size();
        int i = 0;
        String[][] result = new String[size][];

        for (String key : map.keySet()) {
            if (map.get(key) == 1) {
                result[i] = new String[]{key};
            } else {
                result[i] = new String[map.get(key)];
                for (int j = 0; j < map.get(key); j++) {
                    result[i][j] = key;
                }
            }
            i++;
        }

        for (String[] s : result) {
            System.out.println(Arrays.toString(s));
        }
    }
}
