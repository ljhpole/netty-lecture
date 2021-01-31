package com.jdk1_8.functiontest;

public class MeaningTest {

        public final int value = 4;
        public void doIt()
        {
            int value = 6;
            Runnable r = new Runnable(){
                public final int value = 5;
                public void run(){
                    int value = 10;
                    System.out.println(this.value);
                }
            };
            r.run();
        }
        public static void main(String...args)
        {
            MeaningTest m = new MeaningTest();
            m.doIt();
        }

}
