package com.twdt.netty.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by ljhpole on 2020/6/27.
 */
public class NewIOClient {

  public static void main(String[] args) throws IOException {
    SocketChannel socketChannel = SocketChannel.open();
    socketChannel.connect(new InetSocketAddress("localhost",8899));
    socketChannel.configureBlocking(true);
    String fileName = "/Users/ljhpole/Downloads/mac_software/vue_html5/HBuilder.9.1.29.macosx_64.dmg";

    FileChannel fileChannel = new FileInputStream(fileName).getChannel();
    long startTime = System.currentTimeMillis();
    long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
    System.out.println("Send Data BytesNum = [" + transferCount + "],"+" InterverTime = [" + (System.currentTimeMillis()-startTime) + "]");
  }

}
