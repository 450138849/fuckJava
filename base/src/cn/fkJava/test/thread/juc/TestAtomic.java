package cn.fkJava.test.thread.juc;

/**
 * 原子性问题复现
 */
public class TestAtomic {
    public static void main(String[] args) {
        TestThread t = new TestThread();
        for (int i = 0; i < 10; i++) {
            new Thread(t).start();
        }
    }
}

class TestThread implements Runnable {
    volatile int sum = 0;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + ++sum);
    }
}
