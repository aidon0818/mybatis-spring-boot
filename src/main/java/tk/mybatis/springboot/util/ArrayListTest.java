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
        test3();
    }

    public static void test2() {

        List<CartDTO> list1 = new ArrayList<CartDTO>();
        CartDTO cartDTO = new CartDTO("A", 1);
        cartDTO.setAmt(new BigDecimal(1));
        cartDTO.setCreateTime(new Date());
        list1.add(cartDTO);

        List<CartDTO> list2 = new ArrayList<CartDTO>();
        CartDTO cartDTO1 = new CartDTO("A", 1);
        cartDTO1.setAmt(new BigDecimal(2));
        cartDTO1.setCreateTime(new Date());
        list2.add(cartDTO1);

        List<CartDTO> list3 = new ArrayList<CartDTO>();
        list3.addAll(list1);
        list3.addAll(list2);
        List<CartDTO> newList = new ArrayList<CartDTO>();
        List<CartDTO> productIdList = new ArrayList<CartDTO>();
        Set<CartDTO> productId = new TreeSet<>((o1, o2) -> o1.getProductId().compareTo(o2.getProductId()));
        List<CartDTO> productQuantityList = new ArrayList<CartDTO>();
        Set<CartDTO> productQuantity = new TreeSet<>((o1, o2) -> o1.getProductQuantity().compareTo(o2.getProductQuantity()));
        List<CartDTO> amtList = new ArrayList<CartDTO>();
        Set<CartDTO> amt = new TreeSet<>((o1, o2) -> o1.getAmt().compareTo(o2.getAmt()));
        List<CartDTO> createTimeList = new ArrayList<CartDTO>();
        Set<CartDTO> createTime = new TreeSet<>((o1, o2) -> o1.getCreateTime().compareTo(o2.getCreateTime()));
        productId.addAll(list3);
        productQuantity.addAll(list3);
        amt.addAll(list3);
        productIdList.addAll(productId);
        productQuantityList.addAll(productQuantity);
        amtList.addAll(amt);
        createTimeList.addAll(createTime);
        newList.addAll(amtList);
        System.out.println(newList.size());
        for (CartDTO c : newList) {
            System.out.println(c);
        }
    }

    private static void test3() {
        List<CartDTO> list1 = new ArrayList<CartDTO>();
        for (int i = 0; i < 1000; i++) {
            CartDTO cartDTO = new CartDTO("A" + i, 1);
            cartDTO.setAmt(new BigDecimal(i));
            cartDTO.setCreateTime(new Date());
            list1.add(cartDTO);
        }
        List<CartDTO> list2 = new ArrayList<CartDTO>();
        for (int i = 0; i < 998; i++) {
            CartDTO cartDTO1 = new CartDTO("A" + i, 1);
            cartDTO1.setAmt(new BigDecimal(i));
            cartDTO1.setCreateTime(new Date());
            list2.add(cartDTO1);
        }

        List<CartDTO> cartDTOS = new ArrayList<>();
        for (CartDTO c1 : list1) {
            for (CartDTO c2 : list2) {
                if (c2.getProductId().equals(c1.getProductId())) {
                    if (!cartDTOS.contains(c1)) {
                        cartDTOS.add(c1);
                    }
                }
            }
        }
        list1.removeAll(cartDTOS);
        for (CartDTO c : list1) {
            System.out.println(c);
        }
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
