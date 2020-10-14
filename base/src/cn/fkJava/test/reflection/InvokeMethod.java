package cn.fkJava.test.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 调用方法
 */
public class InvokeMethod {
    public static void main(String[] args) throws Exception {
        Person person = new Person();
        /**
         * 调用一般方法
         */
        Method toString = person.getClass().getMethod("toString");
        System.out.println(toString.invoke(person));// Person{age=0, name='null'}
        /**
         * 调用私有方法
         */
        Method print1 = person.getClass().getDeclaredMethod("print1", String.class);//要使用getDeclaredMethod才能访问到private的方法
        print1.setAccessible(true);
        print1.invoke(person, "testArgs");//必须要先设置权限，否则无法调用private权限的方法
        // 报错Exception in thread "main" java.lang.IllegalAccessException
    }
}
