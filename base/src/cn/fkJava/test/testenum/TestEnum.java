package cn.fkJava.test.testenum;

import java.util.Arrays;

public enum TestEnum {
    ZHANGSAN, LISI, WANGWU, ZHAOLIU;

    public static void main(String[] args) {
        System.out.println(TestEnum.ZHANGSAN);// ZHANGSAN
//        Arrays.asList(TestEnum.values()).stream().forEach(System.out::println);
        System.out.println(TestEnum.ZHANGSAN.getDeclaringClass());// class cn.fkJava.test.testenum.TestEnum
        System.out.println(TestEnum.ZHANGSAN.name());// ZHANGSAN
        System.out.println(TestEnum.LISI.ordinal());// 1
        System.out.println(TestEnum.ZHANGSAN.describeConstable());// Optional[EnumDesc[TestEnum.ZHANGSAN]]如果构造方法有第二个参数，则第二个参数为描述
    }
}
