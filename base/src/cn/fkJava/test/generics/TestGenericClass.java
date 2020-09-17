package cn.fkJava.test.generics;

public class TestGenericClass<T> {
    private T t;

    public TestGenericClass() {

    }

    public TestGenericClass(T t) {
        this.t = t;
    }

    private void get() {
        System.out.println(t);
    }

    public static void main(String[] args) {
        TestGenericClass<Integer> t1 = new TestGenericClass(1);
        TestGenericClass<String> t2 =  new TestGenericClass("zhangsan");

        t1.get();
        t2.get();
    }
}
