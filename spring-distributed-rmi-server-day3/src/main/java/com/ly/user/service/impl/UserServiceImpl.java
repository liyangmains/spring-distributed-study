package com.ly.user.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.ly.user.service.UserService;

public class UserServiceImpl extends UnicastRemoteObject implements UserService{

	public UserServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getUserName(String userName) throws RemoteException {
		// TODO Auto-generated method stub
		return "hello:"+userName;
	}

}
