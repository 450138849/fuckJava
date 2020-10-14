package cn.fkJava.test.reflection;

import java.util.Arrays;

/**
 * 获取构造器
 */
public class GetConstructor {
    public static void main(String[] args) {
        Arrays.stream(Person.class.getConstructors()).forEach(System.out::println);//获取public的构造器
        Arrays.stream(Person.class.getDeclaredConstructors()).forEach(System.out::println);//获取全部的构造器
    }
}
