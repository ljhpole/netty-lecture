package com.juc.threadpool.test0;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTaskTest {
    public static void main(String[] args) {
        FutureTask<Integer> task = new FutureTask<>(()->{
            TimeUnit.MILLISECONDS.sleep(5000);
            return 1000;

        });
        System.out.println("Begin ---------- 1");
        task.run();
        //new Thread(task).start();
        try {
            System.out.println("Begin ---------- 3");
            System.out.println(task.get());
            System.out.println("Begin ---------- 4");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
