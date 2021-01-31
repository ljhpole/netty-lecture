package com.hx.disruptor;

import com.lmax.disruptor.EventHandler;

public class LongEventOtherHandler implements EventHandler<LongEvent> {
    public static long anotherCounter = 0;
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        anotherCounter++;
        System.out.println("["+Thread.currentThread().getName()+"] "+event+" another seq："+sequence);
    }
}
