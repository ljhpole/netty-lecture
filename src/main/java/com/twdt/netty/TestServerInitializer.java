package com.twdt.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Created by ljhpole on 2020/6/26.
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();

    pipeline.addLast("HtttpServerCodec",new HttpServerCodec());
    pipeline.addLast("com.twdt.netty.TestHttpServerHandler",new TestHttpServerHandler());
  }
}
