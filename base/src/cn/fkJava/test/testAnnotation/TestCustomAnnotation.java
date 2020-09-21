package cn.fkJava.test.testAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestCustomAnnotation {
    String value() default "a";// 如果这里没有给出defaultValue那么注解后边一定要带上对应的值，否则编译报错

    String nonDefaultValue();
}

class Test {
    // 这里由于没有带上nonDefaultValue的值所以报错
    @TestCustomAnnotation()
    void Test() {

    }

    // value有默认值，所以不填value的值也不报错
    @TestCustomAnnotation(nonDefaultValue = "test")
    void Test2() {

    }

    // 标记注解不用赋值，括号可带可不带
    @TestAnnotation3
    void Test3() {

    }
}
