package com.twdt.netty.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 */
public class OldServer {

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(8899);
    while (true){
      Socket accept = serverSocket.accept();
      DataInputStream dataInputStream = new DataInputStream(accept.getInputStream());
      try{
        byte[] bytes = new byte[4096];
        while (true)
        {
          int readcount = dataInputStream.read(bytes, 0, bytes.length);
          if(-1 == readcount)
            break;
        }
        System.out.println("args = [" + "Thread Exit!" + "]");
      }catch (Exception ex){
        ex.printStackTrace();
      }
    }
  }

}
