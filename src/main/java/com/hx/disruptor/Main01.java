package com.hx.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executors;

public class Main01 {
    public static void main(String[] args) {
        LongEventFactory longEventFactory = new LongEventFactory();
        int buffersize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<>(longEventFactory,buffersize, Executors.defaultThreadFactory());
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        long sequence = ringBuffer.next();

        try{
            LongEvent event = ringBuffer.get(sequence);
            event.setValue(9999L);
        }
        finally {
            ringBuffer.publish(sequence);
        }

    }
}
