package com.twdt.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import java.net.URI;

/**
 * Created by ljhpole on 2020/6/26.
 */

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
    if (msg instanceof HttpRequest) {
      HttpRequest httpRequest = (HttpRequest)msg;
      System.out.println("请求方法名： " + httpRequest.method().name());

      URI uri = new URI(httpRequest.uri());
      ByteBuf content = Unpooled.copiedBuffer("Hello World!", CharsetUtil.UTF_8);
      FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
              HttpResponseStatus.OK, content);
      response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
      response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

      if("/favicon.ico".equals(uri.getPath())){
        System.out.println(uri);
        System.out.println("请求favicon.ico");

      }
      else{
        System.out.println(uri.getPath());
      }




      ctx.writeAndFlush(response);
      ctx.channel().close();

    }
  }


  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channel active! ctx = [" + ctx + "]");
    super.channelActive(ctx);
  }

  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channel registered! ctx = [" + ctx + "]");
    super.channelRegistered(ctx);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    System.out.println("channel inactive! ctx = [" + ctx + "]");
    super.channelInactive(ctx);
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channel unregistered! ctx = [" + ctx + "]");
    super.channelUnregistered(ctx);
  }

  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    System.out.println("handle added! ctx = [" + ctx + "]");
    super.handlerAdded(ctx);
  }
}
