package com.hx.nio.socketchannel;

import org.apache.ibatis.annotations.SelectKey;
import scala.math.Ordering;

import java.io.IOException;
import java.io.StringReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

import static java.nio.channels.SelectionKey.OP_ACCEPT;

public class NioChatServer {
    static void TransferDataToOtherChannel(Selector selector, SocketChannel sendChannel, ByteBuffer sContent){
        Set<SelectionKey> selectionKeys = selector.keys();
        selectionKeys.forEach((selectionKey ->{
            Channel channel = selectionKey.channel();
            if(channel instanceof SocketChannel && sendChannel != channel){
                try {
                    SocketChannel socketChannel = (SocketChannel)channel;
                    sContent.flip();
                    socketChannel.write(sContent);
                    sContent.clear();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        }));
    }
    public static void main(String[] args) {
        try {
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            InetSocketAddress socketAddress = new InetSocketAddress("localhost", 9000);
            ServerSocketChannel bind = serverChannel.bind(socketAddress);
            serverChannel.configureBlocking(false);
            Selector selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true){
                int selectRtn = selector.select(2000);
                if(selectRtn == 0){
                    System.out.println("---- wait .....");
                    continue;
                }
                if(selectRtn > 0){
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()){
                        SelectionKey nextSelect = iterator.next();
                        if(nextSelect.isAcceptable()){
                            SocketChannel acceptChannel = serverChannel.accept();
                            acceptChannel.configureBlocking(false);
                            acceptChannel.register(selector,SelectionKey.OP_READ);
                        }
                        else if(nextSelect.isReadable()){
                            SocketChannel channelRead = (SocketChannel) nextSelect.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            int read = channelRead.read(buffer);

                            String sContent = new String(buffer.array());
                            System.out.println("RecvContent: "+sContent+";ChannelPort:"+channelRead.socket().getPort());
                            TransferDataToOtherChannel(selector,channelRead,buffer);
                        }

                        iterator.remove();
                    }
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
