package com.ly.rmi.yuanma;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Persion_Stub {

	public String lockUp(String key){
		Socket socket = null;
		ObjectOutputStream oos;
		String info = null;
		try {
			socket = new Socket("localhost", 8888);
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject("Persion");
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			info = (String)ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
}
