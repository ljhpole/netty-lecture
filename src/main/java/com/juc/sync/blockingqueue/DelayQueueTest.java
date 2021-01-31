package com.juc.sync.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {
    static BlockingQueue<MyTask>  tasks = new DelayQueue<>();
    static class MyTask implements Delayed{

        String  name;
        long runningTime;
        MyTask(String str,long lrt){
            this.name = str;
            this.runningTime = lrt;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long convert = unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
            return convert;
        }

        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)){
                return -1;
            }else if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)){
                return 1;
            }
            else {
                return 0;
            }

        }
    }

    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask("t1", now + 1000);
        MyTask t2 = new MyTask("t2", now + 8000);
        MyTask t3 = new MyTask("t3", now + 2000);
        MyTask t4 = new MyTask("t4", now + 4000);
        MyTask t5 = new MyTask("t5", now + 500);

        try {
            tasks.put(t1);
            tasks.put(t2);
            tasks.put(t3);
            tasks.put(t4);
            tasks.put(t5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(tasks);
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(tasks.take().name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
