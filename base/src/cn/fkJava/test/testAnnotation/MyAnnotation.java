package cn.fkJava.test.testAnnotation;

import java.lang.annotation.*;

@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
public @interface MyAnnotation {
    String value();
}
