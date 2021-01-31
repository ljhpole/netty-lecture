package com.juc.threadpool;

class Task implements Runnable {
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("start task " + name);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
        }
        System.out.println("end task " + name);
    }
}
