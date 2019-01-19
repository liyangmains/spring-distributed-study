package com.ly.test;

import com.ly.service.impl.PersionServiceImpl;
import com.ly.service.impl.PersionServiceImplService;

public class ClientTest {

	public static void main(String[] args) {
		PersionServiceImplService persionServiceImplService = new PersionServiceImplService();
		PersionServiceImpl persionServiceImpl = persionServiceImplService.getPersionServiceImplPort();
		String userName = persionServiceImpl.getPersion("张飞");
		System.out.println(userName);
		
	}
}
