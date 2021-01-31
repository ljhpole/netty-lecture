package com.jdk1_8.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    String[] elements = new String[10];
    Lock lock = new ReentrantLock();
    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();
    int elementCount;  // elements数组中的元素数量
    int putIndex;
    int takeIndex;
    public void init (){
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
    }
    public  void putElement(String str){
        lock.lock();
        try {
            while (elementCount == elements.length){
                notFull.await();
            }
            elements[putIndex++%(elements.length)] = str;
            System.out.println("Put Element --> ");
            System.out.println(Arrays.toString(elements));
            elementCount++;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public  String takeElement(){
        String result = null ;
        lock.lock();
        try {
            while (elementCount == 0){
                notEmpty.await();
            }
            result = elements[takeIndex%(elements.length)] ;
            elements[takeIndex%(elements.length)] = null;
            System.out.println("Take Element --> ");
            System.out.println(Arrays.toString(elements));
            takeIndex++;
            elementCount--;
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return result;
    }


}
