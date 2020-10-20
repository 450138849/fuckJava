package cn.fkJava.test.thread.ticket;

/**
 * 线程通信--生产者消费者模式
 */
public class ThreadInteraction2 {
    public static void main(String[] args) {
        Product pro = new Product();
        Producer producer = new Producer(pro);
        Consumer consumer = new Consumer(pro);
        new Thread(producer, "生产者").start();
        new Thread(consumer, "消费者").start();
    }
}

class Consumer implements Runnable {
    private Product pro;//资源数量

    public Consumer(Product pro) {
        this.pro = pro;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (pro) {
                if (pro.getNum() == 0) {
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

class Producer implements Runnable {
    private Product pro;//资源数量

    public Producer(Product pro) {
        this.pro = pro;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (pro) {
                if (pro.getNum() == 20) {
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

class Product {
    private int num;//产品数量

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
