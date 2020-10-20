package cn.fkJava.test.thread.juc;

import org.junit.Test;

/**
 * 内存可见性问题复现
 */
public class TestVolatile {
    public static void main(String[] args) {
        testThread testThread = new testThread();
        new Thread(testThread).start();

        while (true) {
            if (testThread.getSum() == 3) {
                System.out.println("--------");
                break;
            }
        }
    }
}

class testThread implements Runnable {
    int sum = 0;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            sum += i;
            System.out.println(Thread.currentThread().getName() + ":" + sum);
        }
    }
}
