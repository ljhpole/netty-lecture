package com.hx.nio.socketchannel;

import org.apache.ibatis.annotations.SelectKey;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioChatClient {

    public static ByteBuffer SendMessage(ByteBuffer buffer) {
        while (true) {
            try {
                if (System.in.read(buffer.array()) == -1) {
                    break;
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return buffer;
    }

    public static void main(String[] args) {
        try {
            SocketChannel socketClient = SocketChannel.open(new InetSocketAddress("localhost", 9000));
            socketClient.configureBlocking(false);
            Selector selectorClient = Selector.open();
            socketClient.register(selectorClient, SelectionKey.OP_READ );
            //private String userName;
            new Thread(() -> {


                while (true) {
                    int selectRtn = 0;
                    try{
                        selectRtn = selectorClient.select(2000);
                    }
                    catch (IOException ioException){

                    }
                    if (selectRtn == 0) {
                        // System.out.println("----client wait .....");
                        continue;
                    }
                    if (selectRtn > 0) {
                        Set<SelectionKey> selectionKeys = selectorClient.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey nextSelect = iterator.next();
                            if (nextSelect.isWritable()) {
//                            SocketChannel channel = (SocketChannel) nextSelect.channel();
//                            Object attachment = nextSelect.attachment();
                                System.out.println("nextSelect.isWritable()");
                            } else if (nextSelect.isReadable()) {
                                SocketChannel channelRead = (SocketChannel) nextSelect.channel();
                                ByteBuffer buffer = ByteBuffer.allocate(1024);
                                int readLength = 0;
                                try {
                                    readLength = channelRead.read(buffer);
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                                String sContent = new String(buffer.array());
                                System.out.println("ChannelPort:[" + channelRead.socket().getRemoteSocketAddress().toString() + "],RecvContent: " + sContent);
                                buffer.clear();
//                            ByteBuffer byteBuffer = SendMessage(buffer);
//
//                            byteBuffer.flip();
//                            try {
//                                channelRead.write(byteBuffer);
//                            } catch (IOException ioException) {
//                                ioException.printStackTrace();
//                            }
//                            byteBuffer.clear();
                            }

                            iterator.remove();
                        }
                    }
                }
            }).start();
            while (true) {
                ByteBuffer bufferRead = ByteBuffer.allocate(1024);
                int read = 0;

                Reader inputStreamReader = new InputStreamReader(System.in);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                try {
                    System.out.println("Please Input Data:");
                    String sContent = bufferedReader.readLine();
                    ByteBuffer put = bufferRead.put(sContent.getBytes());
                    //sContent.toCharArray()
                    put.flip();
                    socketClient.write(put);
                    put.clear();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
