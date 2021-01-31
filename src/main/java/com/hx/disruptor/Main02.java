package com.hx.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.util.concurrent.Executors;

public class Main02 {
    public static void main(String[] args) {
        LongEventFactory longEventFactory = new LongEventFactory();
        int buffersize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<>(longEventFactory,buffersize, DaemonThreadFactory.INSTANCE);
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        // ===================================================================
        EventTranslator<LongEvent> eventTranslator = new EventTranslator<LongEvent>() {
            @Override
            public void translateTo(LongEvent event, long sequence) {
                event.setValue(8888L);
            }
        };
        ringBuffer.publishEvent(eventTranslator);
        // ===================================================================
        EventTranslatorOneArg<LongEvent, Long> eventTranslatorOneArg = new EventTranslatorOneArg<LongEvent, Long>() {

            @Override
            public void translateTo(LongEvent event, long sequence, Long arg0) {
                event.setValue(arg0);
            }
        };
        ringBuffer.publishEvent(eventTranslatorOneArg,9999L);

        // ====================================================================
        EventTranslatorTwoArg<LongEvent, Long, Long> eventTranslatorTwoArg = new EventTranslatorTwoArg<LongEvent, Long, Long>() {
            @Override
            public void translateTo(LongEvent event, long sequence, Long arg0, Long arg1) {
                event.setValue(arg1);
            }
        };
        ringBuffer.publishEvent(eventTranslatorTwoArg,10000L,10000L);


        // ====================================================================
        EventTranslatorVararg<LongEvent> eventTranslatorVararg = new EventTranslatorVararg<LongEvent>() {
            @Override
            public void translateTo(LongEvent event, long sequence, Object... args) {
                long result = 0;
                for (int i = 0; i < args.length; i++) {
                    long l = (Long) args[i];
                    result += l;
                }
                event.setValue(result);
            }
        };
        ringBuffer.publishEvent(eventTranslatorVararg,10000L,10000L,10000L,10000L);
    }
}
