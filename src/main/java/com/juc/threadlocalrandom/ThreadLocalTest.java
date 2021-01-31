package com.juc.threadlocalrandom;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class ThreadLocalTest {
    public static class M{
        public void setName(String name) {
            this.name = name;
        }

        String name;

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }

    @Override
    public String toString() {
        return "M{" +
                "name='" + name + '\'' +
                '}';
    }

    public static ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    }
    public static void initInfo(){
        M m = new M();
        m.name = "ljinghui";
        //threadLocal.set(m);
    }
    public static void main(String[] args) {
//        WeakReference<M> mWeakReference = new WeakReference<>(new M());
//        System.out.println(mWeakReference.get());
//        System.gc();
//        System.out.println(mWeakReference.get());
//initInfo();
//        System.out.println("ttttt "+threadLocal.get().toString());
//        // System.out.println();
//        threadLocal.set(new M());
//        threadLocal.remove();
        ThreadLocal<Object> threadLocal2 = new ThreadLocal<>();
        M m = new ThreadLocalTest.M();
        m.setName("lijinghui000");
        threadLocal2.set(m);
        System.out.println("ttttt "+threadLocal2.get().toString());
        threadLocal2.remove();
        System.gc();
        try {
            System.in.read();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
