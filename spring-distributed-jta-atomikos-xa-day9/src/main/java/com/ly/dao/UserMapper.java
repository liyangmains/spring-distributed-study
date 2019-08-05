package com.ly.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ly.bean.User;

@Mapper
public interface UserMapper {

	void inserUser(User user);
	
	int updateUser(User user);
}
