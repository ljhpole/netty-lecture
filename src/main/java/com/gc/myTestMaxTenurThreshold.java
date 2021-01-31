package com.gc;
/*
-verbose:gc -Xmx200M -Xmn50M -XX:TargetSurvivorRatio=60 -XX:+PrintTenuringDistribution -XX:+PrintGCDetails -XX:+PrintGCDateStamps
 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:MaxTenuringThreshold=3
 */
public class myTestMaxTenurThreshold {
	public static void myGc(){
		for (int i = 0; i < 40; i++) {
			byte[] bytes = new byte[1024 * 1024];
		}
	}
	public static void main(String[] args) throws InterruptedException {
		byte[] bytes = new byte[512 * 1024];
		byte[] bytes1 = new byte[512 * 1024];
		myGc();
		Thread.sleep(1000);
		System.out.println("111111");

		myGc();
		Thread.sleep(1000);
		System.out.println("222222");

		myGc();
		Thread.sleep(1000);
		System.out.println("333333");

		myGc();
		Thread.sleep(1000);
		System.out.println("444444");

		byte[] bytes2 = new byte[1024 * 1024];
		byte[] bytes3 = new byte[1024 * 1024];
		byte[] bytes4 = new byte[1024 * 1024];

		myGc();
		Thread.sleep(1000);
		System.out.println("555555");

		myGc();
		Thread.sleep(1000);
		System.out.println("666666");


	}
}
