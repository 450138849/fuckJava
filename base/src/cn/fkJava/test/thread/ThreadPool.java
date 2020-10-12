package cn.fkJava.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        es.execute(new Thread2());
        es.submit(new Thread4());
        es.shutdown();
    }
}
