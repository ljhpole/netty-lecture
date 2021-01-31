package com.exception.futureexception;

import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

public class SendTest<K,V> {
    public ListenableFuture<SendResult<K, V>> sendMessageInfo(String str){
        SettableListenableFuture<SendResult<K, V>> future = new SettableListenableFuture();
        future.setException(new RuntimeException(str));
        SendResult.Record record = new SendResult.Record<>(str,str);
        SendResult.RecordMeta recordMeta = new SendResult.RecordMeta(str);
        SendResult<K, V> objectObjectSendResult = new SendResult<>(record, recordMeta);
        future.set(objectObjectSendResult);
        return future;
    }

    public void Send(String strInfo){
        ListenableFuture<SendResult<K,V>> listenableFuture = sendMessageInfo(strInfo);
//发送成功回调
        SuccessCallback<SendResult<K,V>> successCallback = new SuccessCallback<SendResult<K,V>>() {
            @Override
            public void onSuccess(SendResult<K,V> result) {
//成功业务逻辑
                System.out.println("OnSuccess Info!");
            }
        };
//发送失败回调
        FailureCallback failureCallback = new FailureCallback() {
            @Override
            public void onFailure(Throwable ex) {
//失败业务逻辑
                System.out.println("-----------------------------------");
                throw new RuntimeException(ex);
            }
        };
        listenableFuture.addCallback(successCallback, failureCallback);

    }
}
