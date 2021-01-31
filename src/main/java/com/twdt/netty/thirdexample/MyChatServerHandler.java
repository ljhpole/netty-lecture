package com.twdt.netty.thirdexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by ljhpole on 2020/6/26.
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

  private  static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
    Channel channel = ctx.channel();
    channelGroup.forEach(ch -> {
      if(ch != channel){
        ch.writeAndFlush(channel.remoteAddress() + " 发送的消息 "+ msg + "\n");
      }
      else
      {
        ch.writeAndFlush("[ 自己 ] 发送的消息 "+ msg + "\n");
      }
    });
  }


  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    Channel channel = ctx.channel();
    channelGroup.writeAndFlush("[Server] -" + channel.remoteAddress() + " add\n");
    channelGroup.add(channel);
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    Channel channel = ctx.channel();
    channelGroup.writeAndFlush("[Server] -" + channel.remoteAddress() + " leave \n");
    channelGroup.remove(channel);
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    Channel channel = ctx.channel();
    System.out.println("RemoteClient = [" + channel.remoteAddress() + "] OnLine ");

    //super.channelActive(ctx);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    Channel channel = ctx.channel();
    System.out.println("RemoteClient = [" + channel.remoteAddress() + "] OffLine ");
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
