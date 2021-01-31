package com.hx.ListenFuture;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureTask;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.concurrent.*;

public class TestFuture {
    public static void main(String[] args) {
        try {
            testListenFuture();
            System.out.println("sout");
            // System.in.read();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    public static void testListenFuture() throws InterruptedException {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        try {
            System.out.println("主任务执行完，开始异步执行副任务1.....");

            ListenableFutureTask<String> future = new ListenableFutureTask(new Task());//(ListenableFuture<String>) pool.submit(new Task());
            future.addCallback(new SuccessCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    System.out.println("成功,结果是:" + result);
                }
            }, new FailureCallback() {
                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("出错,业务回滚或补偿");
                }
            });
            Task2 task2 = new Task2("lijinghui");
            ListenableFutureTask<String> future2 =  new ListenableFutureTask(task2);//(ListenableFuture<String>)pool.submit(new Task2());
            future2.addCallback(new SuccessCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    System.out.println("task2 成功,结果是:" + result);
                }
            }, new FailureCallback() {
                @Override
                public void onFailure(Throwable ex) {

                    System.out.println("task2 出错,业务回滚或补偿");
                    System.out.println(task2.getStrNameInfo());
                }
            });
            pool.submit(future);
            pool.submit(future2);

            System.out.println("副本任务启动,回归主任务线，主业务正常返回2.....");

        }
        finally {
            pool.shutdown();
            pool.awaitTermination(10,TimeUnit.SECONDS);
        }

    }
}
