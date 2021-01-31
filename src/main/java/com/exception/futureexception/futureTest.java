package com.exception.futureexception;


import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

public class futureTest<K,V> {


    public static void main(String[] args) {
        SendTest<String, String> objectObjectSendTest = new SendTest<>();
        try
        {
            objectObjectSendTest.Send("info");
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        System.out.println("main End!");




//        ListenableFuture<SendResult<String, String>> listenableFuture = objectObjectSendTest.sendMessageInfo("info");
////发送成功回调
//        SuccessCallback<SendResult<String, String>> successCallback = new SuccessCallback<SendResult<String, String>>() {
//            @Override
//            public void onSuccess(SendResult<String, String> result) {
////成功业务逻辑
//                System.out.println("OnSuccess Info!");
//            }
//        };
////发送失败回调
//        FailureCallback failureCallback = new FailureCallback() {
//            @Override
//            public void onFailure(Throwable ex) {
////失败业务逻辑
//                System.out.println("-----------------------------------");
//                 throw new RuntimeException(ex);
//            }
//        };
//        listenableFuture.addCallback(successCallback, failureCallback);

    }
}
