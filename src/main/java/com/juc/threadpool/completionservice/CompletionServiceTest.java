package com.juc.threadpool.completionservice;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class CompletionServiceTest {
    public static void main(String[] args) {
        ExecutorService executorService;
        executorService = new ThreadPoolExecutor(5,
                10,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10),
                new ThreadPoolExecutor.AbortPolicy());
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

        IntStream.range(0,9).forEach(i->{
            completionService.submit(()->{
                Thread.sleep((long) (Math.random()*1000));
                System.out.println(Thread.currentThread().getName());
                return i*i;
            });
        });

        IntStream.range(0,9).forEach(i->{
            Integer integer = null;
            try {
                integer = completionService.take().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(integer);
        });

        executorService.shutdown();
    }
}
