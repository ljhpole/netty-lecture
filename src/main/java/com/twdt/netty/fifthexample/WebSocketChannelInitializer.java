package com.twdt.netty.fifthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by ljhpole on 2020/6/27.
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {


  private final SslContext sslCtx;

  public WebSocketChannelInitializer(SslContext sslCtx) {
    this.sslCtx = sslCtx;
  }
  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();
    pipeline.addLast(new HttpServerCodec());
    pipeline.addLast(new ChunkedWriteHandler());
    pipeline.addLast(new HttpObjectAggregator(8192));
    pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
    pipeline.addLast(new WebsocketServerHandler());
  }
}
