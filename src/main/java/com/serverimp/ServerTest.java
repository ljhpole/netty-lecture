package com.serverimp;

public class ServerTest {
    public static void main(String[] args) {
        ServerClassic serverClassic = new ServerClassic();
        Thread serverThread = new Thread(serverClassic);
        serverThread.start();
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
