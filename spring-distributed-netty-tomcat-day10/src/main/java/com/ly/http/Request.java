package com.ly.http;

import java.util.List;
import java.util.Map;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

public class Request{

	private ChannelHandlerContext ctx;
	private HttpRequest request;
	public Request(ChannelHandlerContext ctx,HttpRequest request){
		this.ctx = ctx;
		this.request = request;
	}
	public String getUri(){
		return request.getUri();
	}
	public String getMethod(){
		return request.getMethod().name();
	}
	public Map<String,List<String>> getParameters(){
		QueryStringDecoder decoder = new QueryStringDecoder(request.getUri());
		return decoder.parameters();
	}
	public String getParameter(String name){
		Map<String,List<String>> params = getParameters();
		List<String> param = params.get(name);
		if(null == param){
			return null;
		}else{
			return param.get(0);
		}
	}
}
