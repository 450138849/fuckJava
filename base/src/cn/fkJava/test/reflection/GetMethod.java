package cn.fkJava.test.reflection;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 获取方法运行时的信息
 */
public class GetMethod {
    public static void main(String[] args) throws Exception {
        //获取方法
        Method[] methods = Person.class.getMethods();
        Method[] declaredMethods = Person.class.getDeclaredMethods();
        Method print1 = Person.class.getDeclaredMethod("print1", String.class);
        //获取方法名称
//        Arrays.stream(methods).forEach(x -> System.out.println(x.getName()));//不包含私有方法
//        Arrays.stream(declaredMethods).forEach(x -> System.out.println(x.getName()));//包含私有方法
        //获取方法的注解
        System.out.println(print1.getAnnotation(PersonAnnotation.class));//@cn.fkJava.test.reflection.PersonAnnotation("non-value")
        //获取注解的值
        System.out.println(print1.getAnnotation(PersonAnnotation.class).value());//non-value
        //获取方法的修饰符
        System.out.println(print1.getModifiers());//2
        //获取方法的返回值类型
        System.out.println(print1.getReturnType());//void
        //获取方法的参数列表
        Arrays.stream(print1.getParameterTypes()).forEach(System.out::println);//class java.lang.String
        Arrays.stream(print1.getTypeParameters()).forEach(System.out::println);//该方法用于获取泛型参数
        //获取方法抛出的异常
        Arrays.stream(print1.getExceptionTypes()).forEach(System.out::println);//class java.lang.Exception
    }
}
