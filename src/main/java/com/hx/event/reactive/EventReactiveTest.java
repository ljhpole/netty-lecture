package com.hx.event.reactive;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.GenericApplicationListener;

import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.ResolvableType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventReactiveTest {
    public static void main(String[] args) {
        SimpleApplicationEventMulticaster applicationEventMulticaster = new SimpleApplicationEventMulticaster();
        ApplicationListener genericApplicationListenerAdapter = new GenericApplicationListener() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println(" ===== Current Thread: - "+Thread.currentThread().getName()+" #call back = "+event.getSource().toString());
            }

            @Override
            public boolean supportsEventType(ResolvableType eventType) {
                return true;
            }
        };

        applicationEventMulticaster.addApplicationListener(genericApplicationListenerAdapter);
        applicationEventMulticaster.addApplicationListener(new GenericApplicationListener() {
            @Override
            public boolean supportsEventType(ResolvableType eventType) {
                return true;
            }

            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println(" ***** Current Thread: - "+Thread.currentThread().getName()+" #call back = "+event.getSource().toString());
            }
        });
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        applicationEventMulticaster.setTaskExecutor(executorService);
        applicationEventMulticaster.multicastEvent(new ApplicationEvent("hello word!") {
            @Override
            public Object getSource() {
                return super.getSource();
            }
        });
        System.out.println("==== main end ===");
        executorService.shutdownNow();
    }
}
