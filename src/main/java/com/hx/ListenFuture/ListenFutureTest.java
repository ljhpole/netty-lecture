package com.hx.ListenFuture;




import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ListenFutureTest {

    public static void main(String[] args) {
        try {
            testListenFuture();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    public static void testListenFuture() throws InterruptedException {
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        try {
            System.out.println("主任务执行完，开始异步执行副任务1.....");

            ListenableFuture<String> future =  pool.submit(new Task());
            Futures.addCallback(future, new FutureCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    System.out.println("成功,结果是:" + result);
                }

                @Override
                public void onFailure(Throwable t) {
                    System.out.println("出错,业务回滚或补偿");
                }
            },pool);

            ListenableFuture<String> future2 =  pool.submit(new Task2("lijinghui"));
            Futures.addCallback(future2, new FutureCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    System.out.println("task2 成功,结果是:" + result);
                }

                @Override
                public void onFailure(Throwable t) {
                    System.out.println("task2 出错,业务回滚或补偿");
                }
            },pool);

            System.out.println("副本任务启动,回归主任务线，主业务正常返回2.....");

        }
        finally {

            pool.awaitTermination(10,TimeUnit.SECONDS);
            pool.shutdown();
        }

    }

}

class Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(5);
         //int a =1/0;
        return "task done";
    }
}

class Task2 implements Callable<String> {

    public String getStrNameInfo() {
        return strNameInfo;
    }

    public void setStrNameInfo(String strNameInfo) {
        this.strNameInfo = strNameInfo;
    }

    String  strNameInfo;
    Task2(String name){
        this.strNameInfo = name;
    }
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        int a =1/0;
        return "task2 done";
    }
}