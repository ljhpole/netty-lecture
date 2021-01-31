package com.twdt.netty.secondexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.UUID;

/**
 * Created by ljhpole on 2020/6/26.
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
    System.out.println("remoteAddress = [" + ctx.channel().remoteAddress() + "], msg = [" + msg + "]");
    ctx.channel().writeAndFlush("from server: " + UUID.randomUUID());
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    //super.exceptionCaught(ctx, cause);
    cause.printStackTrace();
    ctx.close();
  }
}
