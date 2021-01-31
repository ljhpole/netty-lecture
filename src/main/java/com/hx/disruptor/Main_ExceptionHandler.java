package com.hx.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Main_ExceptionHandler {
    public static void main(String[] args) throws IOException, InterruptedException {
        LongEventFactory longEventFactory = new LongEventFactory();
        int buffersize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<>(longEventFactory,buffersize, Executors.defaultThreadFactory(),
                ProducerType.MULTI,new SleepingWaitStrategy());


        EventHandler<LongEvent> h1 = (LongEvent event, long sequence, boolean endOfBatch)->{
            System.out.println("Event:"+ event );
            throw new RuntimeException("exception");
        };
        disruptor.handleEventsWith(h1);

        disruptor.handleExceptionsFor(h1).with(new ExceptionHandler<LongEvent>() {
            @Override
            public void handleEventException(Throwable ex, long sequence, LongEvent event) {
                ex.printStackTrace();
            }

            @Override
            public void handleOnStartException(Throwable ex) {
                System.out.println("Exception start to Handle!");
            }

            @Override
            public void handleOnShutdownException(Throwable ex) {
                System.out.println("Exception end to Handle!");
            }
        });
        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        // --------------------------------------------
        final int threadCount = 1;
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
                IntStream.range(0,10).forEach((j)->{
                    ringBuffer.publishEvent((evt,seq)->{
                        evt.setValue(j);
                        //System.out.println("生产了:"+threadnum);
                    });
                });
            });

        });

        executorService.shutdown();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(LongEventHandler.counter);


    }


}
