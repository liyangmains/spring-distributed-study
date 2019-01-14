package com.ly.multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.TimeUnit;

public class MulticaseServer {

	public static void main(String[] args) {
		try {
			//地址段:224.0.0.0-239.255.255.255
			//实现消息群发（主播）为当前ip的客户端都可以接收到
			InetAddress address = InetAddress.getByName("224.5.6.7");
			MulticastSocket socket = new MulticastSocket();
			for(int i = 0 ; i <= 9 ; i++ ){
				String info = "Hello Word";
				byte[] data = info.getBytes();
				socket.send(new DatagramPacket(data,data.length,address,8088));
				TimeUnit.SECONDS.sleep(2);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
