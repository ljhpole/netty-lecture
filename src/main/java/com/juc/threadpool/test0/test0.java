package com.juc.threadpool.test0;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class test0 {
    public static void main(String[] args) {
        String chNum = "123456";
        String chChar = "ABCDEF";
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition conditionNum = reentrantLock.newCondition();
        Condition conditionChar = reentrantLock.newCondition();

        new Thread(()->{
            try {
                reentrantLock.lock();
                IntStream.range(0,chNum.length()).forEach((i)->{
                    System.out.println(chNum.charAt(i));
                    conditionChar.signal();  // move thread from wait queue to sync queue
                    try {
                        conditionNum.await();  //  add wait queue --> Release lock to unpack sync queue ---> blocking park
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                });
                conditionChar.signal();
            }
            finally {
                reentrantLock.unlock();
            }

        },"numPrint").start();

        new Thread(()->{
            try {
                reentrantLock.lock();
                IntStream.range(0,chChar.length()).forEach((i)->{
                    System.out.println(chChar.charAt(i));
                    conditionNum.signal();
                    try {
                        conditionChar.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                });
                conditionNum.signal();
            }
            finally {
                reentrantLock.unlock();
            }

        },"charPrint").start();
    }
}
