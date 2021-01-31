package com.juc.sync.containerTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class TestWithWaitNotify {
    volatile List lists = new ArrayList();
    public void addElement(Object obj){
        lists.add(obj);
    }
    public int listSize(){
        return lists.size();
    }

    public static void main(String[] args) {
        TestWithWaitNotify testWithoutVolatile = new TestWithWaitNotify();
        Object o = new Object();
        new Thread(()->{
            synchronized (o){
                System.out.println("t2 start");
                if(testWithoutVolatile.listSize() != 5) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 Exit");
            }

        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            synchronized (o){
                System.out.println("t1 Enter");
                IntStream.range(0,10).forEach((i)->{
                    testWithoutVolatile.addElement(new Object());
                    System.out.println("add " + i);

                    if(i == 5){
                        o.notify();  // 不释放锁，导致 只能当t1 执行完后，才激活t2继续执行
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                System.out.println("t1 Exit");
            }


        },"t1").start();



    }
}
