package com.ly.rmi.yuanma;

public class RmiClient {
	
	public static void main(String[] args) {
		Persion_Stub stub = new Persion_Stub();
		String info = stub.lockUp("Persion");
		System.out.println(info);
	}
}
