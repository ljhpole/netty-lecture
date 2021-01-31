package com.juc.sync.containerTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.IntStream;

public class TestWithSemaphore {
    volatile List lists = new ArrayList();
    public void addElement(Object obj){
        lists.add(obj);
    }
    public int listSize(){
        return lists.size();
    }
    static Thread t1 = null,t2 = null;
    public static void main(String[] args) {
        TestWithSemaphore testWithoutVolatile = new TestWithSemaphore();

        Semaphore semaphore = new Semaphore(1);


        t1 = new Thread(() -> {
            System.out.println("t1 Enter");
            try {
                semaphore.acquire();
                IntStream.range(0, 5).forEach((i) -> {
                    testWithoutVolatile.addElement(new Object());
                    System.out.println("add " + (i + 1));
                });
                semaphore.release();
                t2.start();
                t2.join();
                semaphore.acquire();
                IntStream.range(5, 10).forEach((i) -> {
                    testWithoutVolatile.addElement(new Object());
                    System.out.println("add " + (i + 1));
                });
                semaphore.release();

                } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 Exit");
        }, "t1");

        t2 = new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("t2 start");
                if(testWithoutVolatile.listSize() == 5){
                    System.out.println("t2 Exit");
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2");

        t1.start();

    }
}
