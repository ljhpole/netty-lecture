package com.juc.waitnotify;

public class DecreaseThread extends Thread {
    private WaitNotifyTest waitNotifyTest;

    DecreaseThread(WaitNotifyTest waitNotifyTest){
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
            this.waitNotifyTest.DecreaseCounterOne();
        }
    }
}
