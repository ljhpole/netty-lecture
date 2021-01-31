package com.juc.interrupt;

public class InterruptExample {
    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MyThread2 extends Thread {
        @Override
        public void run() {
            while (true) {
                if(interrupted()){
                    break;
                }

                // ..
            }
            System.out.println("Thread end");
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new MyThread2();
        thread1.start();
        thread1.interrupt();
        System.out.println("Main run");
    }
}
