package com.ly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ly.bean.User;
import com.ly.entity.ResponseEntity;
import com.ly.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/add")
	public ResponseEntity insertUser(String name,String phone){
		User user = new User();
		user.setName(name);
		user.setPhone(phone);
		ResponseEntity responseEntity = new ResponseEntity();
		userService.inserUser(user);
		if(user.getId() == null){
			responseEntity.setCode(1);
			responseEntity.setContent("添加用户失败");
		}else{
			responseEntity.setCode(0);
			responseEntity.setContent("添加用户成功");
		}
		return responseEntity;
	}
	@GetMapping("/edit")
	public ResponseEntity updateUser(Integer id,String name,String phone){
		ResponseEntity responseEntity = new ResponseEntity();
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setPhone(phone);
		if(user.getId() != null){
			int num = userService.updateUser(user);
			if(num <= 0){
				responseEntity.setCode(1);
				responseEntity.setContent("更新用户失败");	
				return responseEntity;
			}
		}else{
			responseEntity.setCode(1);
			responseEntity.setContent("用户编号不能为空");
			return responseEntity;
		}
		responseEntity.setCode(0);
		responseEntity.setContent("更新用户成功");
		return responseEntity;
	}
}
