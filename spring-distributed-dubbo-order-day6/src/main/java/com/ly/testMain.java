package com.ly;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ly.user.service.UserService;

public class testMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-order.xml");
		UserService userService=(UserService)context.getBean("userService");
		String result = userService.getUserName("张飞");
		System.out.println(result);
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
