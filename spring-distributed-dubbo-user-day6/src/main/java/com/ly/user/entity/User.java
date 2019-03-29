package com.ly.user.entity;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -3174836808110295091L;
	
	private String userName;

	public User() {
		super();
	}

	public User(String userName) {
		super();
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + "]";
	}
	
	
}
