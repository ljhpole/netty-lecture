package com.juc.dcltest;

import java.util.stream.IntStream;

public class DclTest {
    private static /*volatile*/ DclTest INSTANCE_;  // volatile 禁止指令重排序
    private DclTest(){}  //禁止外部new 该类对象
    public DclTest getInstance(){
        if (INSTANCE_ == null){
            synchronized (DclTest.class){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(INSTANCE_ == null){ // 线程安全，避免多个线程轮流获得锁后执行到该步骤生成多个对象
                    INSTANCE_ = new DclTest();  // new DclTest :1)allocate memory;2)Init Memory; 3)INSTANCE_ = memory
                }

            }

        }
        return INSTANCE_;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        DclTest dclTest = new DclTest();

        IntStream.range(0,1000).forEach(i->{
            new Thread(()-> System.out.println(dclTest.getInstance().hashCode())).start();
        });
    }

}
