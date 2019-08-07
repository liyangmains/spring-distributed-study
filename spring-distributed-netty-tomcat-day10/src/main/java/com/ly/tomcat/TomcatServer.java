package com.ly.tomcat;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class TomcatServer {

	private Integer port;
	
	public TomcatServer(Integer port){
		this.port = port;
	}
	
	public void listen(){
		//boss线程（主线程）
		EventLoopGroup boss = new NioEventLoopGroup();
		//worker线程（子线程）
		EventLoopGroup worker = new NioEventLoopGroup();
		try {
			//netty服务
			ServerBootstrap server = new ServerBootstrap();
			//链路式编程
			server.group(boss, worker)
			//主线程处理类
			.channel(NioServerSocketChannel.class)
			//子线程处理类，Handler
			.childHandler(new ChannelInitializer<SocketChannel>() {
	
				@Override
				protected void initChannel(SocketChannel client) throws Exception {
					
					client.pipeline().addLast(new HttpResponseEncoder());
					
					client.pipeline().addLast(new HttpRequestDecoder());
					
					client.pipeline().addLast(new TomcatHandler());
				}
				
			})
			//主线程配置信息,128最大连接数
			.option(ChannelOption.SO_BACKLOG, 128)
			//子线程配置信息
			.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			//同步阻塞等待
			ChannelFuture f = server.bind(this.port).sync();
			System.out.println("tomcat启动成功"+this.port);
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
		
	}
	
	public static void main(String[] args) {
		new TomcatServer(8080).listen();
	}
}
