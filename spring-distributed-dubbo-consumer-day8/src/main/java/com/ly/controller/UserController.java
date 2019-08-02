package com.ly.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ly.entity.LoginEntity;
import com.ly.entity.ResponseEntity;
import com.ly.service.IUserService;

@RestController
public class UserController {
	
	@Reference(version="2")
	private IUserService userService;
	
	@PostMapping("/login")
	public ResponseEntity getLogin(String userName,String password){
		ResponseEntity responseEntity = new ResponseEntity();
		LoginEntity login = new LoginEntity();
		login.setUserName(userName);
		login.setPassword(password);
		responseEntity = userService.getLogin(login);
		return responseEntity;
	}

}
