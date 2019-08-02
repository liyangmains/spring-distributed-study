package com.ly.entity;

import java.io.Serializable;

public class LoginEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8623778844527850488L;
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
