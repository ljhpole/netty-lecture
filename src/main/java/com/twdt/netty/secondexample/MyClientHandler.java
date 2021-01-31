package com.twdt.netty.secondexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.time.LocalDateTime;

/**
 * Created by ljhpole on 2020/6/26.
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
    System.out.println("remoteaddress = [" + ctx.channel().remoteAddress() + "], msg = [" + msg + "]");
    ctx.writeAndFlush("from client: "+ LocalDateTime.now());
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    ctx.writeAndFlush("from client: " + LocalDateTime.now());
    //super.channelActive(ctx);
  }
}
