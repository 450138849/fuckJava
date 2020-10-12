package cn.fkJava.test.testio;

import java.io.Serializable;

public class Person implements Serializable {
    static final long serialVersionUID = 56465465465L;

    String name = "zhangsan";
    int age = 10;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
