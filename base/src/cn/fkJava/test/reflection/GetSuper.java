package cn.fkJava.test.reflection;

/**
 * 获取父类信息
 */
public class GetSuper {
    public static void main(String[] args) {
        System.out.println(Person.class.getSuperclass());// 有父类会继承父类，否则会继承Object
        System.out.println(Person.class.getGenericSuperclass());// 获取泛型类，没有泛型则显示父类名称
    }
}
