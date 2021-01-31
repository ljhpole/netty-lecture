package com.juc.dcltest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class casAtomicTest {
    AtomicInteger counter = new AtomicInteger(0);
    void increaseCounter(){
        IntStream.range(0,10000).forEach((i)->{
            this.counter.incrementAndGet();
        });
    }

    public static void main(String[] args) {
        casAtomicTest casAtomicTest = new casAtomicTest();
        List<Thread> threadList = new ArrayList<>();
        IntStream.range(0,100).forEach((i)->{
            threadList.add(new Thread(casAtomicTest::increaseCounter,"thread"+i));
        });

        threadList.forEach((o)->{o.start();});
        threadList.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(casAtomicTest.counter.get());
    }
}
