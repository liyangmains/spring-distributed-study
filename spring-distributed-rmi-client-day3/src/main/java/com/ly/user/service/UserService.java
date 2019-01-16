package com.ly.user.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote{

	String getUserName(String userName) throws RemoteException;
}
