package com.ly.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocketDemo {

	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket("192.168.20.191", 8088);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
			printWriter.println("helloWord");
			while(true){
				String readerLine = reader.readLine();
				if(readerLine == null){
					break;
				}
				System.out.println(readerLine);
			}
			printWriter.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
