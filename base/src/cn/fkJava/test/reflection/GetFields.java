package cn.fkJava.test.reflection;

import java.lang.reflect.Field;

/**
 * 通过反射获取属性信息
 */
public class GetFields {
    public static void main(String[] args) throws Exception {
        Person person = new Person();
        //获取属性
        Field age = person.getClass().getField("age");
        Field name = person.getClass().getDeclaredField("name");//getDeclaredField可以获取全部属性getField只能获取私有属性
        //获取变量类型
        System.out.println(age.getType());//int
        //获取变量的注解
        System.out.println(age.getAnnotation(PersonAnnotation.class));//@cn.fkJava.test.reflection.PersonAnnotation("non-value")
        //获取变量的名称
        System.out.println(age.getName());//age
        //设置变量的值
        age.set(person, 10);
        name.setAccessible(true);
        name.set(person, "zhangsan");// 如果不设置访问权限则无法设置这个值，报错为Exception in thread "main" java.lang.IllegalAccessException
        //获取变量的值
        System.out.println(age.get(person));//10
        System.out.println(name.get(person));//zhangsan
    }
}
