package com.twdt.netty.fifthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import java.util.Locale;

/**
 * Created by ljhpole on 2020/6/27.
 */
public class WebsocketServerHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
    // ping and pong frames already handled

    if (frame instanceof TextWebSocketFrame) {
      // Send the uppercase string back.
      String request = ((TextWebSocketFrame) frame).text();
      System.out.println("channelRead0 = ["  + request + "]");
      ctx.channel().writeAndFlush(new TextWebSocketFrame(request.toUpperCase(Locale.US)));
    } else {
      String message = "unsupported frame type: " + frame.getClass().getName();
      throw new UnsupportedOperationException(message);
    }
  }

  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    System.out.println("handler added,ID = [" + ctx.channel().id().asLongText() + "]");
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    System.out.println("handler Removed,ID = [" + ctx.channel().id().asLongText() + "]");

  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    super.exceptionCaught(ctx, cause);
    ctx.close();
  }
}
