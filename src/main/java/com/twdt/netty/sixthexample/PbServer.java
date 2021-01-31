package com.twdt.netty.sixthexample;

import com.twdt.netty.thirdexample.MyChatServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by ljhpole on 2020/6/29.
 */
public class PbServer {
  public static void main(String[] args) {
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    try {
      ServerBootstrap serverBootstrap = new ServerBootstrap();
      serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).
          childHandler(new PbServerInitializer());
      ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
      channelFuture.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }

  }
}
