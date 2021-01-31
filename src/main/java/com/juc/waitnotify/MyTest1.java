package com.juc.waitnotify;

public class MyTest1 {

    public static void main(String[] args) {
        WaitNotifyTest waitNotifyTest = new WaitNotifyTest();
        waitNotifyTest.setCounter(0);
        IncreaseThread increaseThread = new IncreaseThread(waitNotifyTest);
        DecreaseThread decreaseThread = new DecreaseThread(waitNotifyTest);
        IncreaseThread increaseThread1 = new IncreaseThread(waitNotifyTest);
        DecreaseThread decreaseThread1 = new DecreaseThread(waitNotifyTest);
        IncreaseThread increaseThread2 = new IncreaseThread(waitNotifyTest);
        DecreaseThread decreaseThread2 = new DecreaseThread(waitNotifyTest);
        increaseThread.start();
        decreaseThread.start();
        increaseThread1.start();
        decreaseThread1.start();
        increaseThread2.start();
        decreaseThread2.start();
    }
}
