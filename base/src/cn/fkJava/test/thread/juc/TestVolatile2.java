package cn.fkJava.test.thread.juc;

/**
 * 内存可见性问题解决方法1：synchronized关键字
 */
public class TestVolatile2 {
    public static void main(String[] args) {
        testThread2 testThread = new testThread2();
        long start = System.currentTimeMillis();
        new Thread(testThread).start();

        while (true) {
            if (testThread.getSum() == 3) {
                long end = System.currentTimeMillis();
                System.out.println("执行时间为：" + (end - start));
                break;
            }
        }
    }
}

class testThread2 implements Runnable {
    int sum = 0;

    public synchronized int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum += i;
            System.out.println(Thread.currentThread().getName() + ":" + sum);
        }
    }
}

