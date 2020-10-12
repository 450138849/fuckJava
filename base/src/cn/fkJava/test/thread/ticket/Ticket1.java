package cn.fkJava.test.thread.ticket;

/**
 * 问题：三个窗口售出一百张票
 * 存在线程安全问题的售票窗
 */
public class Ticket1 implements Runnable {
    private int num = 100;//售票总数

    @Override
    public void run() {
        for (int i = num; i > 0; i--) {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + ":售出第" + num + "张票");
                num--;
            }
        }
    }

    public static void main(String[] args) {
        Ticket1 t1 = new Ticket1();
        new Thread(t1, "窗口1").start();
        new Thread(t1, "窗口2").start();
        new Thread(t1, "窗口3").start();
    }
}
