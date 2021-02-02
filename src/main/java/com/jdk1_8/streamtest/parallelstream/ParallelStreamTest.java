package com.jdk1_8.streamtest.parallelstream;

import java.util.function.Function;
import java.util.stream.LongStream;

public class ParallelStreamTest {
    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }
    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }
    public static long sideParallelEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }
    public static void main(String[] args) {
        System.out.println("SideEffect parallel sum done in: " +
                measureSumPerf(ParallelStreamTest::sideEffectSum, 10_000_000L) +" msecs" );
        System.out.println("SideEffect parallel sum done in: " +
                measureSumPerf(ParallelStreamTest::sideParallelEffectSum, 10_000_000L) +" msecs" );
    }
}
