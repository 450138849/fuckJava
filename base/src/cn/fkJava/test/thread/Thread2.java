package cn.fkJava.test.thread;

/**
 * 实现runnable接口创建线程
 */
public class Thread2 implements Runnable {
    @Override
    public void run() {
        System.out.println("run thread2");
    }

    public static void main(String[] args) {
        Thread2 thread = new Thread2();
        new Thread(thread).run();
    }
}
