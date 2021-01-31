package com.twdt.netty.sixthexample;

import com.twdt.netty.sixthexample.MyDataInfo.Cat;
import com.twdt.netty.sixthexample.MyDataInfo.Dog;
import com.twdt.netty.sixthexample.MyDataInfo.MyMessage;
import com.twdt.netty.sixthexample.MyDataInfo.MyMessage.DataType;
import com.twdt.netty.sixthexample.MyDataInfo.Person;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by ljhpole on 2020/6/29.
 */
public class PbServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {


  @Override
  protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg) throws Exception {
    DataType dataType = msg.getDataType();
    if(dataType == DataType.PersonType){
      Person person = msg.getPerson();
      System.out.println("person.name = [" + person.getName() + "], person.age = [" + person.getAge() + "]" +
          "], person.address = [" + person.getAddress() + "]");
    }
    else  if(dataType == DataType.DogType){
      Dog dog = msg.getDog();
      System.out.println("dog.name = [" + dog.getName() + "], dog.age = [" + dog.getAge() + "]");
    }
    else
    {
      Cat cat = msg.getCat();
      System.out.println("cat.name = [" + cat.getName() + "], cat.city = [" + cat.getCity() + "]");
    }
  }
}
