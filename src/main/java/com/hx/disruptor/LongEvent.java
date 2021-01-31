package com.hx.disruptor;

public class LongEvent {
    public void setValue(long value) {
        this.value = value;
    }

    private long value;


    @Override
    public String toString() {
        return "LongEvent{" + "value=" + value +"}";
    }
}
