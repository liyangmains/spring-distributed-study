package com.ly.chat.server;

import com.ly.chat.server.handler.HttpHandler;
import com.ly.chat.server.handler.WebSocketServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class Server {

	private Integer port;
	public Server(Integer port){
		this.port = port;
	}
	public void listen(){
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		
		try {
			ServerBootstrap server = new ServerBootstrap();
			server.group(boss, worker)
			      .channel(NioServerSocketChannel.class)
			      .option(ChannelOption.SO_BACKLOG, 1024)
			      .childHandler(new ChannelInitializer<SocketChannel>() {
	
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						// TODO Auto-generated method stub
						ChannelPipeline pipeline = ch.pipeline();
						//------------------支持http协议------------------
						//解码编码HTTP请求
						pipeline.addLast(new HttpServerCodec());
						pipeline.addLast(new HttpObjectAggregator(64 * 1024));
						//用于处理一个文件流的handler
						pipeline.addLast(new ChunkedWriteHandler());
						pipeline.addLast(new HttpHandler());
						//------------------支持websocket协议-------------
						pipeline.addLast(new WebSocketServerProtocolHandler("/im"));
						pipeline.addLast(new WebSocketServerHandler());
					}
				});
			ChannelFuture f = server.bind(this.port).sync();
			System.out.println("服务启动成功"+this.port);
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new Server(8080).listen();;
	}
}
