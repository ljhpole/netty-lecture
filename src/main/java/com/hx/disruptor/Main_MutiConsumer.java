package com.hx.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Main_MutiConsumer {
    public static void main(String[] args) throws IOException, InterruptedException {
        LongEventFactory longEventFactory = new LongEventFactory();
        int buffersize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<>(longEventFactory,buffersize, Executors.defaultThreadFactory(),
                ProducerType.MULTI,new SleepingWaitStrategy());

        LongEventHandler h1 = new LongEventHandler();
        LongEventOtherHandler h2 = new LongEventOtherHandler();

        disruptor.handleEventsWith(h1,h2);
        //disruptor.handleEventsWith((ev,seq,endofbatch)->{
//            System.out.println("Event:"+ev);
//        });
        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        // --------------------------------------------
        final int threadCount = 50;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount);
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.range(0,threadCount).forEach((i)->{
            final long threadnum = i;
            executorService.submit(()->{
                System.out.printf("Thread %s ready to start!\n",threadnum);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                IntStream.range(0,100).forEach((j)->{
                    ringBuffer.publishEvent((evt,seq)->{
                        evt.setValue(threadnum);
                        //System.out.println("生产了:"+threadnum);
                    });
                });
            });

        });

        executorService.shutdown();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(LongEventHandler.counter);
        System.out.println(LongEventOtherHandler.anotherCounter);


    }


}
