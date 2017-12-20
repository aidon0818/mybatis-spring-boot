package tk.mybatis.springboot.util;

import java.util.*;

/**
 * 作    者 : DongLiu
 * 日    期 : 2017/12/20 15:49
 * 描    述 :
 */
public class ArrayListTest {
    public static void main(String[] args) {
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
