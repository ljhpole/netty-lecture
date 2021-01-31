package com.twdt.netty.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by ljhpole on 2020/6/27.
 */
public class NioServer {

  public static void main(String[] args) throws IOException {
    InetSocketAddress adress = new InetSocketAddress(8899);
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    ServerSocket socket = serverSocketChannel.socket();
    socket.setReuseAddress(true);
    socket.bind(adress);
    int readCount = 0 ,index = 0,readSum = 0;
    ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
    while (true){
      SocketChannel socketChannel = serverSocketChannel.accept();
      socketChannel.configureBlocking(true);

      readCount = index = readSum = 0;
      byteBuffer.clear();
      while(-1 != readCount){
        try{
          index++;
          readSum += readCount;
          readCount = socketChannel.read(byteBuffer);

          }catch (Exception ex){
          ex.printStackTrace();
        }
        byteBuffer.rewind();
      }
      System.out.println("readSum: = [" + readSum + "]"+" Index:= ["+index+"]");
    }
  }
}
