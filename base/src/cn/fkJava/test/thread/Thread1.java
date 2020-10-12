package cn.fkJava.test.thread;

/**
 * 用thread方式创建线程
 */
public class Thread1 extends Thread{
    @Override
    public void run() {
        System.out.println("run thread");
    }

    public static void main(String[] args) {
        Thread1 thread =  new Thread1();
        new Thread(thread).run();
    }
}
