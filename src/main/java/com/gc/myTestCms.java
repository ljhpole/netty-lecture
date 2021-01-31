package com.gc;
/*
-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC
 */
public class myTestCms {
    public static void main(String[] args) {
        byte[] bytes = new byte[4* 1024 * 1024];
        System.out.println("111111");

        byte[] bytes2 = new byte[4* 1024 * 1024];
        System.out.println("222222");

        byte[] bytes3 = new byte[4* 1024 * 1024];
        System.out.println("333333");

        byte[] bytes4 = new byte[4* 1024 * 1024];
        System.out.println("444444");

        byte[] bytes5 = new byte[4* 1024 * 1024];
        System.out.println("5555555");
    }
}
