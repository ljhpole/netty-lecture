package com.hx.serverclient;

import com.hx.grpc.test.StudentServiceGrpc;
import com.hx.proto.MyRequest;
import com.hx.proto.MyResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class GrpcClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8899).usePlaintext().build();
        StudentServiceGrpc.StudentServiceBlockingStub studentServiceBlockingStub = StudentServiceGrpc.newBlockingStub(channel);
        studentServiceBlockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());

        channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }
}
