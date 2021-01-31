package com.hx.serverclient;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    private Server server;
    private void Start() throws IOException {
        server = ServerBuilder.forPort(8899).addService(new com.hx.grpc.serviceImp.PersonImp()).build().start();
        System.out.println("Server Start ....!");
    }
private void BlockingUntilShutdown() throws InterruptedException {
    if(null != server)
        server.awaitTermination();
}
    public static void main(String[] args) throws IOException, InterruptedException {
        GrpcServer grpcServer = (new GrpcServer());
        grpcServer.Start();
        grpcServer.BlockingUntilShutdown();

    }

}
