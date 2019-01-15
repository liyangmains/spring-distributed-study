package com.ly.multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticaseClient{

	public static void main(String[] args) {
		//地址段:224.0.0.0-239.255.255.255
		try {
			InetAddress address = InetAddress.getByName("224.5.6.7");
			MulticastSocket socket = new MulticastSocket(8088);
			socket.joinGroup(address);
			while(true){
				byte[] data = new byte[256];
				DatagramPacket packet = new DatagramPacket(data, data.length);
				socket.receive(packet);
				System.out.println(new String(packet.getData()));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
