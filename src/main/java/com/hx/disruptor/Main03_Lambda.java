package com.hx.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.io.IOException;

public class Main03_Lambda {
    public static void main(String[] args) throws IOException {
        //LongEventFactory longEventFactory = new LongEventFactory();
        int buffersize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new,buffersize, DaemonThreadFactory.INSTANCE);
        //disruptor.handleEventsWith(new LongEventHandler());
        disruptor.handleEventsWith((ev,seq,endofbatch)->{
            System.out.println("Event:"+ev);
        });
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent((ev,seq)->ev.setValue(100000L));
        System.in.read();


    }
}
