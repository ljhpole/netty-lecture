package com.juc.sync.containerTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class TestWithoutVolatile {
    List lists = new ArrayList();
    public void addElement(Object obj){
        lists.add(obj);
    }
    public int listSize(){
        return lists.size();
    }

    public static void main(String[] args) {
        TestWithoutVolatile testWithoutVolatile = new TestWithoutVolatile();
        new Thread(()->{
            IntStream.range(0,10).forEach((i)->{
                testWithoutVolatile.addElement(new Object());
                System.out.println("add " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("t1 Exit");
        },"t1").start();

        new Thread(()->{
           while (true){
               if(testWithoutVolatile.listSize() == 5){
                   break;
               }
           }
            System.out.println("t2 Exit");
        },"t2").start();

    }
}
