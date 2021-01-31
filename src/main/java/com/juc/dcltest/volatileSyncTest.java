package com.juc.dcltest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.google.common.collect.ComparisonChain.start;

public class volatileSyncTest {
    volatile  int count = 0;
    //synchronized
    synchronized void m(){
        IntStream.range(0,10000).forEach((i)->{count++;});
    }

    public static void main(String[] args) {
        volatileSyncTest volatileSyncTest = new volatileSyncTest();
        List<Thread> threadsObjects = new ArrayList<>();
        IntStream.range(0,10).forEach((i)->{
            Thread thread = new Thread(volatileSyncTest::m);
            threadsObjects.add(thread);
            thread.start();
        });
        IntStream.range(0,10).forEach((i)->{
            threadsObjects.forEach((o)->{
                try {

                    o.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        });
        System.out.println(volatileSyncTest.count);
    }
}
