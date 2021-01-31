package com.hx.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {
    public static void main(String[] args) {
        File file = new File("NioTest.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel channelIn = fileInputStream.getChannel();

            FileOutputStream fileOutputStream = new FileOutputStream("NioTestOut.txt");
            FileChannel channelOut = fileOutputStream.getChannel();

            ByteBuffer byteB = ByteBuffer.allocate(100);
            while (channelIn.read(byteB) != -1){
                byteB.flip();
                channelOut.write(byteB);
                byteB.flip();
                System.out.println("channelIn.read(byteB)");
            }
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
