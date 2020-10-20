package cn.fkJava.test.thread.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition应用
 * 三个线程依次打印ABCABCABCABC...
 */
public class ThreadExchange {
    private int num;
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public ThreadExchange(int num) {
        this.num = num;
    }

    public void loopA(int i) {
        lock.lock();
        try {
            if (num != 1) {
                condition1.await();
            }
            System.out.println("A" + i);
            num = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB(int i) {
        lock.lock();
        try {
            if (num != 2) {
                condition2.await();
            }
            System.out.println("B" + i);
            num = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC(int i) {
        lock.lock();
        try {
            if (num != 3) {
                condition3.await();
            }
            System.out.println("C" + i);
            num = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ThreadExchange thread = new ThreadExchange(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    thread.loopA(i);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    thread.loopB(i);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    thread.loopC(i);
                }
            }
        }).start();
    }
}
