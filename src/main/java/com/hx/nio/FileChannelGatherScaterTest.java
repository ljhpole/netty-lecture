package com.hx.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;

public class FileChannelGatherScaterTest {
    public static void main(String[] args) {
        File file = new File("NioTest.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel channelIn = fileInputStream.getChannel();

            FileOutputStream fileOutputStream = new FileOutputStream("NioTestOut.txt");
            FileChannel channelOut = fileOutputStream.getChannel();

            ByteBuffer byteB0 = ByteBuffer.allocate(10);
            ByteBuffer byteB1 = ByteBuffer.allocate(10);
            ByteBuffer byteB2 = ByteBuffer.allocate(10);
            ByteBuffer[] byteBuffers = new ByteBuffer[]{byteB0,byteB1,byteB2};

            while (channelIn.read(byteBuffers)!=-1){
                Arrays.stream(byteBuffers).forEach((element)->{
                    System.out.println("position:"+element.position()+" ,limit:"+element.limit()+" ,capacity:"+element.capacity());
                    element.flip();
                    try {
                        channelOut.write(element);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    element.flip();
                });
                System.out.println("loop times");
            }
//            while (channelIn.read(byteB) != -1){
//                byteB.flip();
//                channelOut.write(byteB);
//                byteB.flip();
//                System.out.println("channelIn.read(byteB)");
//            }
            fileInputStream.close();
            fileOutputStream.close();
            channelIn.close();
            channelOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }finally {

        }
    }
}
