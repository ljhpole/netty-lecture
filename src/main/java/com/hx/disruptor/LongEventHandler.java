package com.hx.disruptor;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {
    public static long counter = 0;
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        counter++;
        System.out.println("["+Thread.currentThread().getName()+"] "+event+" 序号："+sequence);
        // throw new RuntimeException("exception");
    }
}
