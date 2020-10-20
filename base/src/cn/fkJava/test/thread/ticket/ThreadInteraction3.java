package cn.fkJava.test.thread.ticket;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程通信--生产者消费者模式
 * 解决虚假唤醒问题
 */
public class ThreadInteraction3 {
    public static void main(String[] args) {
        Product2 pro = new Product2();
        Producer2 producer = new Producer2(pro);
        Consumer2 consumer = new Consumer2(pro);
        Producer2 producer1 = new Producer2(pro);
        Consumer2 consumer1 = new Consumer2(pro);
        new Thread(producer, "生产者").start();
        new Thread(consumer, "消费者").start();
        new Thread(producer1, "生产者1").start();
        new Thread(consumer1, "消费者1").start();
    }
}

class Consumer2 implements Runnable {
    private Product2 pro;//资源数量

    public Consumer2(Product2 pro) {
        this.pro = pro;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (pro) {
                while (pro.getNum() <= 0) {
                    try {
                        System.out.println("等待生产者生产...");
                        pro.wait();// wait方法应该放在循环当中，唤醒之后要判断一次条件
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                pro.setNum(pro.getNum() - 1);
                System.out.println("消费了一个包子，剩余" + pro.getNum() + "个");
                pro.notify();

            }
        }
    }
}

class Producer2 implements Runnable {
    private Product2 pro;//资源数量

    public Producer2(Product2 pro) {
        this.pro = pro;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (pro) {
                while (pro.getNum() >= 20) {
                    try {
                        System.out.println("等待消费者消费...");
                        pro.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                pro.setNum(pro.getNum() + 1);
                System.out.println("生产了一个包子，剩余" + pro.getNum() + "个");
                pro.notify();
            }
        }
    }
}

/**
 * 使用condition进行重写
 */
class Producer3 implements Runnable {
    private Product2 pro;//资源数量
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public Producer3(Product2 pro) {
        this.pro = pro;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();// 中间包含同步操作
            try {
                while (pro.getNum() >= 20) {
                    try {
                        System.out.println("等待消费者消费...");
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                pro.setNum(pro.getNum() + 1);
                System.out.println("生产了一个包子，剩余" + pro.getNum() + "个");
                condition.signal();
            } finally {
                // 在finally中释放锁保证锁一定会被释放
                lock.unlock();
            }

        }
    }
}

class Product2 {
    private int num;//产品数量

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
