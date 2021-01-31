package com.jdk1_8.test3;

import java.util.stream.IntStream;

public class ConditionTestMain {
    public static void main(String[] args) {
        ConditionTest conditionTest = new ConditionTest();
//        new Thread(()->{
//            for (int i = 0; i < 20; i++) {
//                conditionTest.putElement("a");
//                System.out.println("put element a");
//            }
//        },"put element").start();
//
//        new Thread(()->{
//            for (int i = 0; i < 20; i++) {
//                System.out.println(conditionTest.takeElement());
//            }
//        },"take element").start();
        IntStream.range(0,5).forEach((i)->{new Thread(()->{conditionTest.takeElement();}).start();});
        IntStream.range(0,10).forEach((i)->{new Thread(()->{conditionTest.putElement("hello");}).start();});

    }
}
