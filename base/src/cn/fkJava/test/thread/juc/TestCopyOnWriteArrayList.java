package cn.fkJava.test.thread.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 添加的操作多时候会开销过大，每次添加会重新复制，遍历操作多的时候选用
 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
//        TestCopy testCopy = new TestCopy();
        TestCopy2 test2 = new TestCopy2();
        for (int i = 0; i < 2; i++) {
//            new Thread(testCopy).start();
            new Thread(test2).start();
        }
    }
}

class TestCopy implements Runnable {
    static List list = Collections.synchronizedList(new ArrayList());
    // 初始化顺序：静态->非静态->构造器
    // 父类->子类

    static {
        list.add("aa");
        list.add("aa");
        list.add("aa");
    }

    @Override
    public void run() {
        Iterator iterator = list.iterator();
        // 同步类使用迭代器进行遍历的时候添加元素会出现并发修改异常ConcurrentModificationException
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        list.add("aa");
    }
}

class TestCopy2 implements Runnable {
    static CopyOnWriteArrayList list = new CopyOnWriteArrayList();
    // 初始化顺序：静态->非静态->构造器
    // 父类->子类

    static {
        list.add("aaa");
        list.add("aaa");
        list.add("aaa");
    }

    @Override
    public void run() {
        Iterator iterator = list.iterator();
        // 同步类使用迭代器进行遍历的时候添加元素会出现并发修改异常ConcurrentModificationException
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        list.add("aa");
    }
}


