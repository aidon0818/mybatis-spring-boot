package tk.mybatis.springboot.util;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @Auther: ld
 * @Date: 2019/3/13 10:39
 * @Param ${tags}
 * @Description:
 */
public class TreeSetTest {
    public static void main(String[] args) {
        test();
    }
    private static void test(){
        String str="8 10 15 5 2 7";
        String[] datas = str.split(" ");

        TreeSet tree = new TreeSet();
        for(int i = 0 ; i<datas.length ; i++){
            tree.add(Integer.parseInt( datas[i])); // 字符串转int类型数据是需要使用Integer.parseInt()
        }

        //遍历treeSet的元素拼接成对应的字符串
        Iterator it = tree.iterator();
        while(it.hasNext()){
            System.out.print(it.next()+" ");
        }
    }
}
