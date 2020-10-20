package cn.fkJava.test.thread;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class TestForkJoin {
    @Test
    public void testForkJoin() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0L, 100000000L);
        Long result = forkJoinPool.invoke(task);// 获取计算的返回值
//        Long result = ForkJoinPool.commonPool().invoke(task); //使用这种方式也可以触发任务
        System.out.println(result);
    }

    // jdk8写法
    @Test
    public void test2() {
        Long sum = LongStream.rangeClosed(0L, 100000000L)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println(sum);
    }
}

class ForkJoinSumCalculate extends RecursiveTask<Long> {
    private long start;
    private long end;
    private long mid;
    private static final long THRESHOLD = 3L;

    ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            mid = (end + start) >> 1;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, mid);
            left.fork();// 拆分并压入线程队列
            ForkJoinSumCalculate right = new ForkJoinSumCalculate(mid + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
