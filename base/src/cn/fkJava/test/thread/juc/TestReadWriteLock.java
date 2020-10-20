package cn.fkJava.test.thread.juc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class TestReadWriteLock {
    private int num;
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public TestReadWriteLock(int num) {
        this.num = num;
    }

    public void get() {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        readLock.lock();
        try {
            System.out.println("------" + num);
        } finally {
            readLock.unlock();
        }
    }

    public void set(int num) {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            this.num = num;
            System.out.println(num);
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        TestReadWriteLock test = new TestReadWriteLock(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    test.get();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.set(10);
            }
        }).start();
    }
}
