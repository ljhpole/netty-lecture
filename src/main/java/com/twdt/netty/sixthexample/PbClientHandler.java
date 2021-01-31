package com.twdt.netty.sixthexample;

import com.twdt.netty.sixthexample.MyDataInfo.MyMessage;
import com.twdt.netty.sixthexample.MyDataInfo.MyMessage.DataType;
import com.twdt.netty.sixthexample.MyDataInfo.Person;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Random;

/**
 * Created by ljhpole on 2020/6/29.
 */
public class PbClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {


  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {

    MyDataInfo.MyMessage myMessage = null;
    int randomint = new Random().nextInt(3);

    if(0 == randomint){
      myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(DataType.PersonType)
          .setPerson(MyDataInfo.Person.newBuilder().setName("lijinghui").setAge(36).setAddress("上海").build()).build();
      //MyDataInfo.MyMessage.parseFrom()
    }
    else if(1 == randomint){
      myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(DataType.DogType)
          .setDog(MyDataInfo.Dog.newBuilder().setName("wangwang").setAge(5).build()).build();
    }
    else
    {
      myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(DataType.CatType)
          .setCat(MyDataInfo.Cat.newBuilder().setName("wangwang").setCity("shanghai").build()).build();
    }

    ctx.channel().writeAndFlush(myMessage);
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg) throws Exception {

  }
}
