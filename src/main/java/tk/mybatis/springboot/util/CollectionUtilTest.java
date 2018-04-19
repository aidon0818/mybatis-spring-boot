package tk.mybatis.springboot.util;


import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 作    者 : DongLiu
 * 日    期 : 2018/4/19 14:39
 * 描    述 : 数组、list的操作工具类CollectionUtil
 */
public class CollectionUtilTest {
    public static void main(String[] args) {
        String[] a = new String[]{"a", "b", "c", "d"};
        String[] b = new String[]{"v", "b", "d", "a"};
        List<String> lsitA = Arrays.asList(a);
        List<String> lsitB = Arrays.asList(b);
        //并集
        System.out.println(CollectionUtils.union(lsitA, lsitB));
        //交集
        System.out.println(CollectionUtils.intersection(lsitA, lsitB));
        //补集
        System.out.println(CollectionUtils.disjunction(lsitA, lsitB));
        //差集
        System.out.println(CollectionUtils.subtract(lsitA, lsitB));
    }
}
