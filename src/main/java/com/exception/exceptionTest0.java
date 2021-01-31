package com.exception;

public class exceptionTest0 {
    static void throwRunTimeExceptionTest(){
        throw new RuntimeException("throwRunTimeExceptionTest()");
    }
    public static void main(String[] args) {
        try {
            //int result = 5/0;
            throwRunTimeExceptionTest();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        // int result = 5/0;
        // throwRunTimeExceptionTest();
        System.out.println("exeption test0");
    }
}
