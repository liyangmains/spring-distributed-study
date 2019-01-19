package com.ly.test;

import javax.xml.ws.Endpoint;

import com.ly.service.impl.PersionServiceImpl;

public class ServiceTest {

	public static void main(String[] args) {
		//http://localhost:8888/hello?wsdl获取wsdl文档
		Endpoint.publish("http://localhost:8888/hello", new PersionServiceImpl());
		System.out.println("success");
	}
}
