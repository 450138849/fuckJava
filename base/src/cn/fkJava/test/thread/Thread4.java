package cn.fkJava.test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现Callable接口
 * 优点：可以有返回值，可以抛出异常，支持泛型
 */
public class Thread4 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("启动线程");
        return 1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread4 t4 = new Thread4();
        FutureTask ft = new FutureTask(t4);
        new Thread(ft).start();//ft实现了runnable接口，所以可以传入Thread的构造方法
        System.out.println(ft.get());//FutureTask.get()可以获取call方法的返回值
    }
}
