package cn.fkJava.test.testAnnotation;

        import java.lang.annotation.*;

public class TestMetaAnnotation {
    @Target(ElementType)
    @Retention(RetentionPolicy)
    @Documented
    @Inherited// 修饰的注解都可以被子类继承
}