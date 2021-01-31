package com.twdt.netty.fourexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by ljhpole on 2020/6/27.
 */
public class MyHeartbeatServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if(evt instanceof IdleStateEvent){
      IdleStateEvent event = (IdleStateEvent)evt;

      String eventType = null;
      switch(event.state()){
        case READER_IDLE:
          eventType = "读空闲";
          break;
        case WRITER_IDLE:
          eventType = "Write Idle !";
          break;
        case ALL_IDLE:
          eventType = "Read or Write Idle!";
          break;
      }
      System.out.println("Remote Client = [" + ctx.channel().remoteAddress() + "], evt = [" + eventType + "]");
      ctx.channel().close();
    }
  }
}
