package cn.fkJava.test.thread.lock;

/**
 * 两个方法同时执行的时候，由于互相持有对方需要的锁，导致程序锁死，如果一边先执行完另外一边也可以执行完
 */
public class DeadLock {
    private static Object left = new Object();
    private static Object right = new Object();

    public void leftRight() throws InterruptedException {
        synchronized (left) {
            System.out.println("a");
            Thread.sleep(100);
            synchronized (right) {
                System.out.println("b");
            }
        }
    }

    public void rightLeft() throws InterruptedException {
        synchronized (right) {
            System.out.println("c");
            Thread.sleep(100);
            synchronized (left) {
                System.out.println("d");
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new DeadLock().rightLeft();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new DeadLock().leftRight();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
