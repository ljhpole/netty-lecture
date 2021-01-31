package com.twdt.netty.thirdexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by ljhpole on 2020/6/26.
 */
public class MyChatClientHandler extends SimpleChannelInboundHandler {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("Server = [" + ctx.channel().remoteAddress() + "],receive msg = [" + msg + "]");
  }
}
