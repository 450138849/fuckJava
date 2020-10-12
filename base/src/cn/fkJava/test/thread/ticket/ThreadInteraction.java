package cn.fkJava.test.thread.ticket;

/**
 * 线程交互案例
 */
public class ThreadInteraction {
    public static void main(String[] args) {
        TestThread1 t1 = new TestThread1();
        new Thread(t1, "窗口1").start();
        new Thread(t1, "窗口2").start();
    }
}

class TestThread1 implements Runnable {
    private int num = 100;

    @Override
    public void run() {
        while (num > 0) {
            synchronized (this) {
                this.notify();//注意notify() wait() notifyAll()都必须要指定调用对象，并且要与锁对象一致
                System.out.println(Thread.currentThread().getName() + "：" + num);
                num--;
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
