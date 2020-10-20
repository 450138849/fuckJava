package cn.fkJava.test.thread.juc;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁场景--计算五个线程执行的总时间
 */
public class TestCountDownLatch {
    /**
     * 不加闭锁
     * 结果为：
     * 0
     */
    @Test
    public void test1() {
        TestLatch latch = new TestLatch();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            new Thread(latch).start();
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    /**
     * 加上闭锁：
     * 结果为:
     * Thread-0
     * Thread-2
     * Thread-4
     * Thread-3
     * Thread-1
     * 5045
     */
    @Test
    public void test2() {
        CountDownLatch latch = new CountDownLatch(5);
        TestLatch2 demo = new TestLatch2(latch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            new Thread(demo).start();
        }

        try {
            latch.await();// 这里使用闭锁阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}

class TestLatch implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TestLatch2 implements Runnable {
    private CountDownLatch latch;

    public TestLatch2(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }
    }
}
