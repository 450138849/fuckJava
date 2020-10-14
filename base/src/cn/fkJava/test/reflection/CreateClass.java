package cn.fkJava.test.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 通过反射的方式创建类
 */
public class CreateClass {
    public static void main(String[] args) throws Exception {
        /**
         *  通过反射使用不同的构造器创建对象
         */
        Person person = Person.class.newInstance();// 调用空的构造器方法
        Constructor personConstructor = person.getClass().getDeclaredConstructor(int.class, String.class);// 调用指定参数类型的构造器
        Person person2 = (Person) personConstructor.newInstance(10, "zhangsan");
        System.out.println(person);
        System.out.println(person2);
    }
}






