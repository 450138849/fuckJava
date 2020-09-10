package cn.fkJava.ooptest;

public class LocalOuter {
    // 内部类定义在方法中，只能在方法内部调用
    public void showInner() {
        int a = 10;
        class Inner {
            public void show() {
                System.out.println("show inner!" + a);
            }
        }
        Inner inner = new Inner();
        inner.show();
    }
}
