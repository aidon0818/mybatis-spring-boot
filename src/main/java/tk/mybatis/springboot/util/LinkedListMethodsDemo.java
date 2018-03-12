package tk.mybatis.springboot.util;

import java.util.LinkedList;

/**
 * 作    者 : DongLiu
 * 日    期 : 2018/3/12 19:02
 * 描    述 :
 */
public class LinkedListMethodsDemo {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("first");
        linkedList.add("second");
        linkedList.add("third");
        System.out.println(linkedList);

        linkedList.addFirst("addFirst");
        System.out.println(linkedList);

        linkedList.addLast("addLast");
        System.out.println(linkedList);

        linkedList.add(2, "addByIndex");
        System.out.println(linkedList);
        //删除
        linkedList.remove();
        System.out.println("remove: " + linkedList);

        linkedList.remove("second");
        System.out.println("remove(Object): " + linkedList);

        linkedList.remove("six");
        System.out.println("remove(Object) not exist: " + linkedList);

        linkedList.remove(2);
        System.out.println("remove(index): " + linkedList);

        linkedList.removeFirst();
        System.out.println("removeFirst: " + linkedList);

        linkedList.removeLast();
        System.out.println("removeLast:" + linkedList);

        linkedList.removeFirstOccurrence("first");
        System.out.println("removeFirstOccurrence: " + linkedList);

        linkedList.removeLastOccurrence("first");
        System.out.println("removeLastOccurrence: " + linkedList);

        //get方法
        linkedList.get(3);
        System.out.println("get(index): " + linkedList.get(3));

        linkedList.getFirst();
        System.out.println("getFirst: " + linkedList.getFirst());

        linkedList.getLast();
        System.out.println("getLast: " + linkedList.getLast());

    }
}
