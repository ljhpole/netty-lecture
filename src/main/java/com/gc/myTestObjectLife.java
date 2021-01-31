package com.gc;
/*
-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC
 */
public class myTestObjectLife {

    public static void main(String[] args) {
        while (true){
            byte[] obj = CreateObject.GetObject(4);
            byte[] bytes = new byte[2];
            bytes[0] = obj[0];
            bytes[1] = obj[1];

            
            new Thread(()->{
                for (int i = 0; i < bytes.length; i++) {
                    System.out.println("Second Thread:"+ bytes[i]);
                }
                // System.out.println(bytes.getClass());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            System.out.println("main thread: "+CreateObject.icount);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // obj = null;
        }
    }
}
