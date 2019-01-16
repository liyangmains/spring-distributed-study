package com.ly.test;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import com.ly.user.service.UserService;
import com.ly.user.service.impl.UserServiceImpl;

public class UserRmiServer {
	
	
	public static void main(String[] args) {
		try {
			UserService userService = new UserServiceImpl();
			LocateRegistry.createRegistry(8888);
			Naming.bind("rmi://localhost:8888/user", userService);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
