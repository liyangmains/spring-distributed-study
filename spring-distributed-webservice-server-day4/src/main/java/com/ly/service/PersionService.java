package com.ly.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface PersionService {

	@WebMethod
	String getPersion(String userName);
}
