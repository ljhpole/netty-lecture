package com.juc.threadpool.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinTest {
    public static void main(String[] args) throws Exception {
        // 创建2000个随机数组成的数组:
        long startTime = System.currentTimeMillis();
        long[] array = new long[2000000];
        long expectedSum = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = random();
            expectedSum += array[i];
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Expected sum: " + expectedSum+  " in " + (endTime - startTime) + " ms.");
        // fork/join:
        ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
        startTime = System.currentTimeMillis();
        Long result = ForkJoinPool.commonPool().invoke(task);
        endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
        new Random(0).wait();
    }

    static Random random = new Random(0);

    static long random() {
        return random.nextInt(100);
    }
}
