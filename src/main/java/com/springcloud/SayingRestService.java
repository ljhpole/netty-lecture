package com.springcloud;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public interface SayingRestService {
    String say(String msg);

    public static void main(String[] args) {
        try {
            Method method = SayingRestService.class.getMethod(("say"), String.class);
            Parameter parameter = method.getParameters()[0];
            System.out.println(parameter.getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
