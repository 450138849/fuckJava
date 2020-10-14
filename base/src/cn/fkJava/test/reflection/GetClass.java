package cn.fkJava.test.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 通过反射获取运行时类的属性:
 */
public class GetClass {
    public static void main(String[] args) {
        //获取权限修饰符
        int modifier = Person.class.getModifiers();
        System.out.println(modifier);// 0(表示public)
        //获取类名
        String name = Person.class.getName();
        String name2 = Person.class.getSimpleName();
        System.out.println(name);//cn.fkJava.test.reflection.Person
        System.out.println(name2);//Person
        //获取实现的接口
        Class<?>[] interfaces = Person.class.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface.getName());//Serializable
        }
        //获取公共变量
        Field[] fields = Person.class.getFields();
        Arrays.stream(fields).forEach(System.out::println);//public int cn.fkJava.test.reflection.Person.age
        //获取全部变量
        Field[] declaredFields = Person.class.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(System.out::println);
        /**
         * public int cn.fkJava.test.reflection.Person.age
         * private java.lang.String cn.fkJava.test.reflection.Person.name
         */
        //public int cn.fkJava.test.reflection.Person.age
        // 获取构造器
        Constructor[] personCons = Person.class.getDeclaredConstructors();
        for (Constructor personCon : personCons) {
            System.out.println(personCon);
        }
        /**
         * public cn.fkJava.test.reflection.Person()
         * public cn.fkJava.test.reflection.Person(int,java.lang.String)
         */


    }
}

