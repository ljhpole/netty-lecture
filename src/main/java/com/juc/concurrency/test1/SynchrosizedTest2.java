package com.juc.concurrency.test1;
/*
1、若一个Class类实例对象有若干个synchronized方法时，若被多个线程同时访问，只有一个线程可以获取该对象的锁；多个对象的锁是独立的，多个方法分别访问时没有影响。
2、若一个Class类对象有一个静态的synchronized方法时，对应的是Class对象的锁，与该类的实例对象的锁并不是同一把锁。
 */
public class SynchrosizedTest2 {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        MyClass myClass1 = new MyClass();
        Thread1 thread1 = new Thread1(myClass);
        Thread2 thread2 = new Thread2(myClass);

        thread1.start();
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}


class MyClass {
    public synchronized void hello(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
    }

    public synchronized void world(){
        System.out.println("world");
    }
}


class Thread1 extends Thread{
    private MyClass myClass;
    public Thread1(MyClass myClass){
        this.myClass = myClass;
    }

    @Override
    public void run() {
        this.myClass.hello();
        this.myClass.world();
        System.out.println("Thread1");
    }
}

class Thread2 extends Thread{
    private MyClass myClass;
    public Thread2(MyClass myClass){
        this.myClass = myClass;
    }
    @Override
    public void run() {
        this.myClass.world();
    }
}