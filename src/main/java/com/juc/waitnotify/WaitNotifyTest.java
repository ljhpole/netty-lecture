package com.juc.waitnotify;

public class WaitNotifyTest {

        public int counter;

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public synchronized void AddCounterOne(){
            while(this.counter != 0){
                try {
                    wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            this.counter ++;
            System.out.println(this.counter);
            notify();
        }
        public synchronized void DecreaseCounterOne(){
            while (this.counter == 0){
                try {
                    wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            this.counter--;
            System.out.println(this.counter);
            notify();
        }

}
