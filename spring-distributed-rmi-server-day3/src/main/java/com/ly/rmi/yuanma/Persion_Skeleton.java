package com.ly.rmi.yuanma;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Persion_Skeleton extends Thread{
	
	private Persion persion;
	
	public Persion_Skeleton(Persion persion){
		this.persion=persion;
	}

	@Override
	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8888);
			Socket socket = serverSocket.accept();
			while(socket != null){
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				String key = (String)ois.readObject();
				if(key != null && key.equals("Persion")){
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(persion.getUserName());
					oos.flush();
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
