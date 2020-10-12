package cn.fkJava.test.thread.ticket;

/**
 * 问题：三个窗口售出一百张票
 * 解决线程安全的售票窗
 */

import java.util.concurrent.locks.ReentrantLock;

/**
 * 用同步代码块的方式解决线程安全问题
 */
class Ticket2Imp1 implements Runnable {
    private int num = 1000;//售票总数
    Object obj = new Object();

    @Override
    public void run() {
        for (int i = num; i > 0; i--) {
            // 锁一定要使用相同的对象，如果使用thread方式由于不能传this指代唯一对象，可以使用类名.class获取Class对象，该对象唯一
            synchronized (obj) {
                if (num > 0) {
                    System.out.println(Thread.currentThread().getName() + ":售出第" + num + "张票");
                    num--;
                }
            }
        }
    }
}

/**
 * 使用同步方法来解决线程同步问题
 */
class Ticket2Imp2 implements Runnable {
    private int num = 1000;//售票总数
    Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            show();
        }
    }

    public synchronized void show() {//这里使用的锁是this
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + ":售出第" + num + "张票");
            num--;
        }
    }
}

class Ticket2Imp3 extends Thread {
    private static int num = 1000;//售票总数

    @Override
    public void run() {
        while (true) {
            show();
            if (num == 0) {
                break;
            }
        }
    }

    public static synchronized void show() {//由于使用了static,这里的锁不可能调用this，这里使用的锁是Ticket2Imp3.class
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + ":售出第" + num + "张票");
            num--;
        }
    }
}

/**
 * 同步锁方式
 */
class Ticket2Imp4 implements Runnable {
    private static int num = 1000;//售票总数
    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + ":售出第" + num + "张票");
                num--;
            } else {
                break;
            }
            lock.unlock();
        }
    }
}

public class Ticket2 {
    public static void main(String[] args) {
//        Ticket2Imp3 t1 = new Ticket2Imp3();
//        Ticket2Imp3 t2 = new Ticket2Imp3();
//        Ticket2Imp3 t3 = new Ticket2Imp3();
        Ticket2Imp4 t4 = new Ticket2Imp4();
//        t1.start();
//        t2.start();
//        t3.start();
//        new Thread(t1, "窗口1").start();
//        new Thread(t1, "窗口2").start();
//        new Thread(t1, "窗口3").start();
        new Thread(t4, "窗口1").start();
        new Thread(t4, "窗口2").start();
        new Thread(t4, "窗口3").start();
    }
}
