package com.ly.user.service.impl;


import org.springframework.stereotype.Service;

import com.ly.user.entity.User;
import com.ly.user.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Override
	public String getUserName(String userName) {
		// TODO Auto-generated method stub
		System.out.println("正在访问");
		return new User(userName).toString();
	}

}
