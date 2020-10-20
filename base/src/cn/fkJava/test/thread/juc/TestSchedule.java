package cn.fkJava.test.thread.juc;


import java.util.Random;
import java.util.concurrent.*;

/**
 * 线程调度
 */
public class TestSchedule {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(5);
        //submit  execute --执行 schedule --调度
        for (int i = 0; i < 5; i++) {
            ScheduledFuture<Integer> task = schedule.schedule(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    System.out.println(Thread.currentThread().getName());
                    return new Random().nextInt(50);
                }
            }, 3, TimeUnit.SECONDS);

            System.out.println(task.get());
        }
        schedule.shutdown();
    }
}
