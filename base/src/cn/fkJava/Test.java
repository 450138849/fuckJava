package cn.fkJava;


import cn.fkJava.ooptest.AnonymousClass;

public class Test {
    public static void main(String[] args) {
        AnonymousClass anonymous = new AnonymousClass() {
            @Override
            public void show() {
                System.out.println("show anonymousClass!");
            }
        };
        anonymous.show();
    }
}