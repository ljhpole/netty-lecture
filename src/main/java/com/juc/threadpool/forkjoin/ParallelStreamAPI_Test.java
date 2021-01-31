package com.juc.threadpool.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class ParallelStreamAPI_Test {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        Random random = new Random();
        IntStream.range(0,100000).forEach((i)->{
            nums.add(1000000 + random.nextInt(1000000));
        });
        long l = System.currentTimeMillis();
        nums.parallelStream().forEach(ParallelStreamAPI_Test::isPrime);
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);

        long l2 = System.currentTimeMillis();
        nums.stream().forEach(ParallelStreamAPI_Test::isPrime);
        long l3 = System.currentTimeMillis();
        System.out.println(l3-l2);
    }
    static boolean isPrime(int num){
        for (int i = 2; i < num/2; i++) {
            if(num%i == 0) {
                return false;
            }
        }
        //System.out.println(num);
        return true;
    }
}
