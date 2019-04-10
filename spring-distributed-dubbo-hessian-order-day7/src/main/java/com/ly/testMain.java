package com.ly;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.rpc.RpcContext;
import com.ly.user.service.UserService;

public class testMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-order.xml");
//		UserService userService=(UserService)context.getBean("userService");
//		String result = userService.getUserName("张飞");
//		System.out.println(result);
//		UserService userService2=(UserService)context.getBean("userService2");
//		String result2 = userService2.getUserName("张飞2");
//		System.out.println(result2);
		
//		UserService userService3=(UserService)context.getBean("userService3");
//		String result3 = userService3.getUserName(null);
//		System.out.println("----------------异步请求----------------");
//		System.out.println(result3);
//		Future<String> future =RpcContext.getContext().getFuture();
//		try {
//			String a = future.get();
//			System.out.println(a);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (ExecutionException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			System.in.read();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		//配合 spring-distrbuted-dubbo-hession-user1-day7实现负载均衡
		UserService userService=(UserService)context.getBean("userService");
		for(int i = 0 ; i < 10 ; i++){
			String result = userService.getUserName("张飞");
			System.out.println(result);
		}
	}
}
