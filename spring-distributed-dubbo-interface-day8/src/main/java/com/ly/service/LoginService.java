package com.ly.service;

import com.ly.entity.LoginEntity;
import com.ly.entity.ResponseEntity;

public interface LoginService {
	
	ResponseEntity login(LoginEntity login);
}
