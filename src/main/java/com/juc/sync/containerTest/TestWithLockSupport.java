package com.juc.sync.containerTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.IntStream;

public class TestWithLockSupport {
    volatile List lists = new ArrayList();
    public void addElement(Object obj){
        lists.add(obj);
    }
    public int listSize(){
        return lists.size();
    }

    public static void main(String[] args) {
        TestWithLockSupport testWithoutVolatile = new TestWithLockSupport();
        Thread t1;
        CountDownLatch countDownLatch = new CountDownLatch(1);


        t1 = new Thread(() -> {
            System.out.println("t1 Enter");
            IntStream.range(0, 10).forEach((i) -> {
                testWithoutVolatile.addElement(new Object());
                System.out.println("add " + (i + 1));

                if (testWithoutVolatile.listSize() == 5) {
                    // 打开门
                    countDownLatch.countDown();
                    LockSupport.park();
                }

            });
            System.out.println("t1 Exit");
        }, "t1");

        new Thread(()->{
            System.out.println("t2 start");
            if(testWithoutVolatile.listSize() != 5) {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            LockSupport.unpark(t1);
            System.out.println("t2 Exit");
        },"t2").start();

        t1.start();

    }
}
