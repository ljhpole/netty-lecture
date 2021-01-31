package com.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static void main(String[] args) {
        // 创建一个固定大小的线程池:
        ExecutorService es = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            System.out.println("before es.submit(new Task):" + i);
            es.submit(new Task("" + i));
            System.out.println("after es.submit(new Task):" + i);
        }
        // 关闭线程池:
        es.shutdown();
    }
}

