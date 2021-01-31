package com.twdt.netty.sixthexample;

import com.google.protobuf.MessageLite;
import com.twdt.netty.sixthexample.MyDataInfo.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import java.awt.Frame;
import java.util.List;

/**
 * Created by ljhpole on 2020/6/29.
 */
public class PbSecondProtobufCodec extends MessageToMessageCodec<MyMessage,MessageLite> {

  @Override
  protected void encode(ChannelHandlerContext ctx, MessageLite msg, List<Object> out)
      throws Exception {

  }

  @Override
  protected void decode(ChannelHandlerContext ctx, MyMessage msg, List<Object> out)
      throws Exception {

  }

}
