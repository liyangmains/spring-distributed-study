package com.ly.serviceImpl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.ly.entity.LoginEntity;
import com.ly.entity.ResponseEntity;
import com.ly.service.IUserService;

@Service(version="1")
@Component
public class UserServiceImpl implements IUserService{

	@Override
	public ResponseEntity getLogin(LoginEntity loginEntity) {
		ResponseEntity responseEntity = new ResponseEntity();
		if(StringUtils.isEmpty(loginEntity.getUserName()) || StringUtils.isEmpty(loginEntity.getPassword())){
			responseEntity.setCode(100001);
			responseEntity.setContent("账号或密码不能为空");
		}
		responseEntity.setCode(100000);
		responseEntity.setContent("登录成功,版本1");
		return responseEntity;
	}

}
