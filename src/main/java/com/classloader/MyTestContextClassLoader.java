package com.classloader;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;
/*
ContextClassLoader的作用是为了破坏Java的类加载委托机制
当高层提供了统一的接口让底层去实现，同时又需要再高层加载（或实例化）底层的类时，就必须通过线程上下文类来帮助高层的classloader找到并加载到该类
 */
public class MyTestContextClassLoader {
    public static void main(String[] args) {
        // Thread.currentThread().setContextClassLoader(MyTestContextClassLoader.class.getClassLoader().getParent());
        ServiceLoader<Driver> load = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = load.iterator();
        while(iterator.hasNext()){
            Driver next = iterator.next();
            System.out.println("Driver: "+next.getClass()+",loader: "+next.getClass().getClassLoader());
        }
        System.out.println("Current Thread Context ClassLoader: "+Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader ClassLoader: "+ServiceLoader.class.getClassLoader());
    }
}
