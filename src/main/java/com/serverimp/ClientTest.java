package com.serverimp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ClientTest {

    public static void main(String[] args) {
        Socket socketClient = new Socket();
        SocketAddress socketAddress = new InetSocketAddress("localhost",8080);
        try {
             socketClient.connect(socketAddress);
             socketClient.getOutputStream().write("process".getBytes());
             socketClient.getOutputStream().flush();
            byte[] bytes = new byte[1024];
            int readLen = socketClient.getInputStream().read(bytes);
            System.out.println(readLen);
            Thread.sleep(10000);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
