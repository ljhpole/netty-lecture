package com.jdk1_8.streamtest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectorsTest {
    private static Boolean  bIsPrime(Integer Candidate){
        int sqrt = (int)Math.sqrt((double)Candidate);
        return IntStream.range(2,sqrt+1).noneMatch(i->
           Candidate%i==0
        );
    }

    private static Map<Boolean, List<Integer>>  partitionByPrime(int n){
        return IntStream.rangeClosed(2,n).boxed().collect(Collectors.partitioningBy(element->CollectorsTest.bIsPrime((Integer) element)));
    }
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }
    public static void main(String[] args) {
       // bIsPrime(6);
//        Map<Boolean, List<Integer>> booleanListMap = CollectorsTest.partitionByPrime(100);
//        System.out.println(booleanListMap.get(true));
//        System.out.println(booleanListMap.get(false));

        System.out.println(parallelSum(1000000));
    }
}
