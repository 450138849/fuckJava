package cn.fkJava.test.oop;

public class Outer {
    public void show() {
        System.out.println("show outer!");
    }

    public class Inner {
        private int age;

        public void show() {
            System.out.println("show inner!");
        }
    }
}


