package com.juc.sync;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class cyclicbarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(20, () -> {
            System.out.println("满人");
        });
        for (int i = 0; i < 100; i++) {
            new Thread(()-> {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
