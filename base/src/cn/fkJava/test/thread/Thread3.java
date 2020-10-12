package cn.fkJava.test.thread;

/**
 * 线程优先级
 */
public class Thread3 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        Thread3 thread1 = new Thread3();
        Thread3 thread2 = new Thread3();

        thread1.setName("线程-1");
        thread2.setName("线程-2");
        System.out.println(thread1.getPriority());//默认优先级是5
        System.out.println(thread2.getPriority());
        thread1.setPriority(MAX_PRIORITY);//MAX_PRIORITY 10 NORM_PRIORITY 5 MIN_PRIORITY 1
        thread2.setPriority(MIN_PRIORITY);//优先级越大的大概率优先执行，但是不能绝对保证

        thread1.start();
        thread2.start();
    }
}
