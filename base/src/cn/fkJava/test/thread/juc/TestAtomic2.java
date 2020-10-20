package cn.fkJava.test.thread.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子性问题解决方案--使用原子类
 */
public class TestAtomic2 {
    public static void main(String[] args) {
        TestThread2 t = new TestThread2();
        for (int i = 0; i < 10; i++) {
            new Thread(t).start();
        }
    }
}

class TestThread2 implements Runnable {
    AtomicInteger sum = new AtomicInteger(0);// 默认为0

    public AtomicInteger getSum() {
        return sum;
    }

    public void setSum(AtomicInteger sum) {
        this.sum = sum;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + sum.incrementAndGet());
    }
}
