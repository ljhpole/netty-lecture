package com.juc.sync;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    static Lock reentrantLock = new ReentrantLock();

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();
    private static int value;

    public static int ReadData(Lock lock){
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return  0;
    }

    public static void WriteData(Lock lock,int newValue){
        try {
            lock.lock();
            Thread.sleep(1000);
            value = newValue;
            System.out.println("write over! new value: " + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable target;
        Thread[]  threads = new Thread[20];
        Runnable readR = ()->ReadData(reentrantLock);
        Runnable writeR = ()->WriteData(reentrantLock,new Random().nextInt());
        Runnable readR1 = ()->ReadData(readLock);
        Runnable writeR1 = ()->WriteData(writeLock,new Random().nextInt());
        long lStart = System.currentTimeMillis();
        for (int i = 0; i < 18; i++) {
            threads[i]=new Thread(readR);
            threads[i].start();
        }
        for (int i = 0; i < 2; i++) {
            threads[18+i] = new Thread(writeR);
            threads[18+i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis()-lStart);

        lStart = System.currentTimeMillis();
        for (int i = 0; i < 18; i++) {
            threads[i]=new Thread(readR1);
            threads[i].start();
        }
        for (int i = 0; i < 2; i++) {
            threads[18+i] = new Thread(writeR1);
            threads[18+i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis()-lStart);

    }
}
