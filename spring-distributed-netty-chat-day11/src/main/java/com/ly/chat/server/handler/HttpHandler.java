package com.ly.chat.server.handler;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.LastHttpContent;

public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest>{

	private URL baseURL = HttpHandler.class.getProtectionDomain().getCodeSource().getLocation();
	private final String WEB_ROOT = "webroot";
	
	private File getFileFromRoot(String fileName) throws Exception{
		String path = baseURL.toURI() + WEB_ROOT + "/" + fileName;
		path = !path.startsWith("file:") ? path : path.substring(5);
		path = path.replaceAll("//", "/");
		return new File(path);
	}
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		String uri = request.getUri();
		String page = uri.equals("/") ? "chat.html" : uri;
		RandomAccessFile file = new RandomAccessFile(getFileFromRoot(page),"r");
		String contextType = "text/html;";
		
		if(uri.endsWith(".css")){
			contextType = "text/css;";
		}else if(uri.endsWith(".js")){
			contextType = "text/javascript;";
		}else if(uri.toLowerCase().matches("(jpg|png|gif)$")){
			String ext = uri.substring(uri.lastIndexOf("."));
			contextType = "image/"+ext+";";
		}
		HttpResponse response = new DefaultHttpResponse(request.getProtocolVersion(), HttpResponseStatus.OK);
		response.headers().set(HttpHeaders.Names.CONTENT_TYPE,contextType + "charset=utf-8;");
		//判断是否长链接
		boolean keepAlive = HttpHeaders.isKeepAlive(request);
		if(keepAlive){
			response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,file.length());
			response.headers().set(HttpHeaders.Names.CONNECTION,HttpHeaders.Values.KEEP_ALIVE);
		}
		ctx.write(response);
		ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
		//清空缓冲区
		ChannelFuture f = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
		if(!keepAlive){
			f.addListener(ChannelFutureListener.CLOSE);
		}
		file.close();
	}

}
