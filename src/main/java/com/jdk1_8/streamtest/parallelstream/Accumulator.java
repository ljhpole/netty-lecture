package com.jdk1_8.streamtest.parallelstream;

public class Accumulator {
    public long total = 0;
    public void add(long value) { total += value; }
}
