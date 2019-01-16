package com.ly.test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.ly.user.service.UserService;

public class UserRmiClient {
	
	
	public static void main(String[] args) {
		try {
			UserService userService = (UserService)Naming.lookup("rmi://localhost:8888/user");
			String userName = userService.getUserName("张飞");
			System.out.println(userName);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
