package com.juc.waitnotify;

public class IncreaseThread extends Thread {
    private WaitNotifyTest waitNotifyTest;

    IncreaseThread(WaitNotifyTest waitNotifyTest){
        this.waitNotifyTest = waitNotifyTest;
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep((long) (Math.random()*1000));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.waitNotifyTest.AddCounterOne();
        }
    }
}
