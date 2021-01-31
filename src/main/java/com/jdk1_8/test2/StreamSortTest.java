package com.jdk1_8.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class StreamSortTest {
    public static void main(String[] args) {
           int max = 1000000;
            List<String> values = new ArrayList<>(max);
            for (int i = 0; i < max; i++) {
                UUID uuid = UUID.randomUUID();
                values.add(uuid.toString());
            }
            // 纳秒
            long t0 = System.nanoTime();
            long count = values.stream().sorted().count();
            System.out.println(count);
            long t1 = System.nanoTime();
            // 纳秒转微秒
            long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
            System.out.println(String.format("顺序流排序耗时: %d ms", millis));
            //顺序流排序耗时: 712 ms

    }
}
