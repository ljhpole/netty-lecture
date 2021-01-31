package com.twdt.netty.sixthexample;

import com.twdt.netty.thirdexample.MyChatClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ljhpole on 2020/6/29.
 */
public class PbClient {

  public static void main(String[] args) {
    EventLoopGroup clientGroup = new NioEventLoopGroup();

    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group(clientGroup).channel(NioSocketChannel.class).handler(new PbClientInitializer());
      ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
      channelFuture.channel().closeFuture().sync();

    } catch (InterruptedException e) {
      e.printStackTrace();
    }  finally {
      clientGroup.shutdownGracefully();
    }
  }
}
