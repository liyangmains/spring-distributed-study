package com.ly.user.service.impl;


import org.springframework.stereotype.Service;

import com.ly.user.service.UserService;

@Service("userService3")
public class UserServiceImpl3 implements UserService{

	@Override
	public String getUserName(String userName) {
		// TODO Auto-generated method stub
		System.out.println("正在访问3异步处理");
		return "异步处理成功！";
	}

}
