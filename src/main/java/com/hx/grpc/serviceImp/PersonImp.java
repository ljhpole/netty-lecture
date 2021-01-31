package com.hx.grpc.serviceImp;

import com.hx.grpc.test.StudentServiceGrpc;
import com.hx.proto.MyResponse;
import io.grpc.stub.StreamObserver;

public class PersonImp extends StudentServiceGrpc.StudentServiceImplBase {
    @Override
    public void getRealNameByUsername(com.hx.proto.MyRequest request, StreamObserver<com.hx.proto.MyResponse> responseObserver) {
        //super.getRealNameByUsername(request, responseObserver);
        System.out.println("Server Receive Request = " + request.getUsername() );
        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();
    }
}
