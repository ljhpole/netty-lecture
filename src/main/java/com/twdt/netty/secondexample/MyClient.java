package com.twdt.netty.secondexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by ljhpole on 2020/6/26.
 */
public class MyClient {

  public static void main(String[] args) {
    EventLoopGroup clienGroup = new NioEventLoopGroup();

    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group(clienGroup).channel(NioSocketChannel.class).handler(new MyClientInitializer());

      ChannelFuture channelFuture = bootstrap.connect("localhost",8080).sync();
      channelFuture.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }finally {
      clienGroup.shutdownGracefully();
    }
  }
}
