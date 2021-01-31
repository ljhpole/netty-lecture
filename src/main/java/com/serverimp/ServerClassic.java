package com.serverimp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClassic implements Runnable {
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while (!Thread.interrupted()){
                Socket acceptSocket = serverSocket.accept();
                new Thread(new Handler(acceptSocket)).start();
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    static class Handler implements Runnable{
        final Socket socketClient;

        Handler(Socket socketClient) {
            this.socketClient = socketClient;

        }

        @Override
        public void run() {
            try {
                byte[] input = new byte[1024];
                this.socketClient.getInputStream().read(input);
                byte[] output = process(input);
                socketClient.getOutputStream().write(output);
            } catch (IOException ex) { /* ... */ }
        }

        public byte[] process(byte[] input){
            System.out.println("byte[] process(byte[] input)");
            return input;
        }
    }
}
