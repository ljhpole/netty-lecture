package com.serverimp.rectorimpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadPoolHandler implements Runnable{

        // uses util.concurrent thread pool
        static ExecutorService pool = Executors.newFixedThreadPool(5);
        static final int PROCESSING = 3;

    @Override
    public void run() {

    }
    // ...
//        synchronized void read() { // ...
//            socket.read(input);
//            if (inputIsComplete()) {
//                state = PROCESSING;
//                pool.execute(new Processer());
//            }
//        }
//        synchronized void processAndHandOff() {
//            process();
//            state = SENDING; // or rebind attachment
//            sk.interest(SelectionKey.OP_WRITE);
//        }
//
//    class Processer implements Runnable {
//            public void run() { processAndHandOff(); }
//        }

}
