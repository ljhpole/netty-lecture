package com.juc.deadlock;

public class mytest2 {
    private Object object1 = new Object();
    private Object object2 = new Object();

    public void method1(){
        synchronized (object1){
            synchronized (object2){
                System.out.println("mytest2:method1:invoke()");
            }
        }
    }
    public void method2(){
        synchronized (object2){
            synchronized (object1){
                System.out.println("mytest2:method2:invoke()");
            }
        }
    }

    public static void main(String[] args) {
        mytest2 mytest2 = new mytest2();
        Runnable run1 = ()->{
            while (true){
                mytest2.method1();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(run1,"myThread1").start();

        Runnable run2 = ()->{
            while (true){
                mytest2.method2();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(run2,"myThread2").start();


    }
}
