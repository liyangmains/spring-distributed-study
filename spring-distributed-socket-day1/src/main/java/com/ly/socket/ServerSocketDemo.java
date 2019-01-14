package com.ly.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketDemo {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8088);
			while(true){
				Socket socket = serverSocket.accept();//等待一个接收请求
				new Thread(()->{
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
						while(true){
							String readLine = reader.readLine();//读取客户端发送的消息
							if(readLine == null){
								break;
							}
							System.out.println(readLine);
							printWriter.println("服务端收到:"+readLine);
							printWriter.flush();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
				}).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(serverSocket != null){
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
