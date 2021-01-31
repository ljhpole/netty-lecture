package com.juc.staticvariable;

import java.util.stream.IntStream;

public class staticVariableTest {
    private  static int num; //静态变量
    static Object static_o = new Object();

    public static void main(String[] args) {
        Object o = new Object();
        IntStream.range(0,50).forEach((i)->{
            new Thread(()->{
                synchronized (static_o) {
                    num = 3;
                    System.out.println("current thread: " + Thread.currentThread().getName() + ",num的值是: " + num);
                    num = 5;
                    System.out.println("current thread: " + Thread.currentThread().getName() + ",num的值是: " + num * 2);
                }
            },"Thread-"+i).start();
        });
    }
}
