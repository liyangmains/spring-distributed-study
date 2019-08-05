package com.ly.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.annotation.TargetDataSource;
import com.ly.bean.User;
import com.ly.constant.Data;
import com.ly.dao.UserMapper;
import com.ly.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;

	@Override
	@TargetDataSource(name = Data.DATASOURCE1)
	public User inserUser(User user) {
		// TODO Auto-generated method stub
		userMapper.inserUser(user);
		return user;
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateUser(user);
	}

}
