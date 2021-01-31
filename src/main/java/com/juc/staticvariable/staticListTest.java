package com.juc.staticvariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class staticListTest {
    public static List<String> resList = new ArrayList<>();
    private  static int num; //静态变量
    static Object static_o = new Object();

    public static void main(String[] args) {
        Object o = new Object();
        IntStream.range(0,50).forEach((i)->{
            new Thread(()->{
                synchronized (resList) {
                    System.out.println("current thread: " + Thread.currentThread().getName() + " Enter " );
                    for (int j = 0; j < 500; j++) {
                        resList.add(Thread.currentThread().getName()+j);
                    }
                    System.out.println("current thread: " + Thread.currentThread().getName() + " Exit " );

                }
            },"Thread-add "+i).start();
        });
        IntStream.range(0,1).forEach((i)->{
            new Thread(()->{
                while (true) {

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (resList) {
                        System.out.println("current thread: " + Thread.currentThread().getName() + " Enter,size: " + resList.size());
                        for (int j = 0; j < resList.size(); j++) {

                            //System.out.println(resList.get(j));
                            resList.remove(j);
                            //
                        }
                        System.out.println("current thread: " + Thread.currentThread().getName() + " Exit ");
                    }
                }
            },"Thread-remove "+i).start();
        });
    }
}
