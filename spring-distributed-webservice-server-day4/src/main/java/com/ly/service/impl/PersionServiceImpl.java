package com.ly.service.impl;

import javax.jws.WebService;

import com.ly.service.PersionService;

@WebService
public class PersionServiceImpl implements PersionService{

	@Override
	public String getPersion(String userName) {
		// TODO Auto-generated method stub
		return userName;
	}

}
