package com.juc.threadlocalrandom;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class ThreadLocalRandomTest {
    public static void main(String[] args) {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        IntStream.range(0,20).forEach((i)->{
            System.out.println(threadLocalRandom.nextInt(100));
        });
    }
}
