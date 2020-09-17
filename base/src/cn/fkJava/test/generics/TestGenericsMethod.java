package cn.fkJava.test.generics;

public class TestGenericsMethod {
    public static <T> void printStr(T str){
        System.out.println(str);
    }

    public static void main(String[] args) {
        Integer a = 1;
        String str = "abc";
        Double b = 0.1;

        printStr(a);
        printStr(b);
        printStr(str);
    }
}
