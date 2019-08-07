package com.ly.http;


import io.netty.handler.codec.http.HttpHeaders.Names;
import io.netty.handler.codec.http.HttpHeaders.Values;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

public class Response {

	private ChannelHandlerContext ctx;
	private HttpRequest request;
	public Response(ChannelHandlerContext ctx,HttpRequest request){
		this.ctx = ctx;
		this.request = request;
	}
	public void write(String out) throws Exception{
		try{
			if(out == null){
				return;
			}
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, 
					                        HttpResponseStatus.OK,
					                        Unpooled.wrappedBuffer(out.getBytes("UTF-8"))
					                        );
			response.headers().set(Names.CONTENT_TYPE,"text/json");
			response.headers().set(Names.CONTENT_LENGTH,response.content().readableBytes());
			response.headers().set(Names.EXPIRES,0);
			if(HttpHeaders.isKeepAlive(request)){
				response.headers().set(Names.CONNECTION,Values.KEEP_ALIVE);
			}
			ctx.write(response);
		}finally {
			ctx.flush();
		}
	}
}
