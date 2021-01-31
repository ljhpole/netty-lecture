package com.juc.sync.containerTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class TestWithCountDownLatch {
    volatile List lists = new ArrayList();
    public void addElement(Object obj){
        lists.add(obj);
    }
    public int listSize(){
        return lists.size();
    }

    public static void main(String[] args) {
        TestWithCountDownLatch testWithoutVolatile = new TestWithCountDownLatch();
        //final Object o = new Object();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(()->{
            System.out.println("t2 start");
                if(testWithoutVolatile.listSize() != 5) {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                countDownLatch.countDown();
                System.out.println("t2 Exit");
                countDownLatch.countDown();
            System.out.println("t2 Exit countDownLatch.countDown() again");
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println("t1 Enter");
                IntStream.range(0,10).forEach((i)->{
                    testWithoutVolatile.addElement(new Object());
                    System.out.println("add " + (i+1));

                    if(testWithoutVolatile.listSize() == 5){
                        // 打开门
                        countDownLatch.countDown();
                        try {
                            // 给t1关门，让t2有机会执行
                            TimeUnit.MILLISECONDS.sleep(1);
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                });
                System.out.println("t1 Exit");
         },"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            System.out.println("t3 Enter");
            IntStream.range(10,15).forEach((i)->{
                testWithoutVolatile.addElement(new Object());
                System.out.println("add " + (i+1));

                if(testWithoutVolatile.listSize() == 10){
                    // 打开门
                    countDownLatch.countDown();
                    try {
                        // 给t1关门，让t2有机会执行
                        TimeUnit.MILLISECONDS.sleep(1);
                        countDownLatch.await();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
            System.out.println("t3 Exit");
        },"t3").start();
    }
}
