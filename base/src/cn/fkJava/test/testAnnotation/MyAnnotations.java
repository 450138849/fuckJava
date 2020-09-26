package cn.fkJava.test.testAnnotation;

import javax.swing.text.Element;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE,ElementType.PARAMETER,ElementType.CONSTRUCTOR, ElementType.TYPE_PARAMETER,ElementType.TYPE_USE})
public @interface MyAnnotations {
    MyAnnotation[] value();
}

// 这里是TYPE_PARAMETER
class TestXX<@MyAnnotation(value="c") T>{
    @MyAnnotation(value="a")
    @MyAnnotation(value="b")
    public static void main(String[] args) {

    }

    // 这里是PARAMETER
    void test(@MyAnnotation(value="a") int a){
        double aaa = (@MyAnnotation(value="c") double) a;// 这里要用TYPE_USE
    }
}