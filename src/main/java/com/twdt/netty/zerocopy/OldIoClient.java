package com.twdt.netty.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 */
public class OldIoClient {

  public static void main(String[] args) throws IOException {
    try {
      Socket socket = new Socket("localhost", 8899);
      String fileName = "/Users/ljhpole/Downloads/mac_software/vue_html5/HBuilder.9.1.29.macosx_64.dmg";
      FileInputStream fileInputStream = new FileInputStream(fileName);

      DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
      byte[] buffer = new byte[4096];
      long readcount = 0 ;
      long total = 0;
      long startTime = System.currentTimeMillis();

      while ((readcount = fileInputStream.read(buffer)) >= 0){
        total += readcount;
        dataOutputStream.write(buffer);
      }

      System.out.println("interval time  = [" + (System.currentTimeMillis() - startTime) + "]");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
