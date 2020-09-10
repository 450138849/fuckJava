package cn.fkJava.ooptest;

public class Singleton2 {
    private static Singleton2 singleton;

    private Singleton2() {

    }

    public static Singleton2 getInstance() {
        if (singleton == null) {
            singleton = new Singleton2();
        }
        return singleton;
    }
}
