package cn.fkJava.test.collection;

import java.util.ArrayList;
import java.util.List;

public class TestArrayList {
    public static void main(String[] args) {
        List list = new ArrayList();
        List list2 = new ArrayList();
        list.add(1);
        list2.add(1);
        list2.add(2);// 添加一个元素
        list.add(0, 3);// 往指定下标添加元素
        list.addAll(list2);// 添加一个集合

        list.forEach(System.out::println);
    }
}