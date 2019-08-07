package com.ly.tomcat;


import com.ly.http.MyServlet;
import com.ly.http.Request;
import com.ly.http.Response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

public class TomcatHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof HttpRequest){
			HttpRequest r = (HttpRequest)msg;
			Request request = new Request(ctx,r);
			Response response = new Response(ctx,r);
			new MyServlet().doGet(request, response);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}

	
}
