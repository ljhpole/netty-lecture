package com.twdt.netty.fourexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;

/**
 * Created by ljhpole on 2020/6/27.
 */
public class MyHeartbeatServerInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();

    pipeline.addLast(new IdleStateHandler(5,7,3, TimeUnit.SECONDS));
    pipeline.addLast(new MyHeartbeatServerHandler());
  }
}
