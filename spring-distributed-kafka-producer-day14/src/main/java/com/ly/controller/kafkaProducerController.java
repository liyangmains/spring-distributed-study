package com.ly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class kafkaProducerController {

	@Autowired
	private KafkaTemplate<Integer,String> kafkaTemplate;
	
	@GetMapping("send")
	public String send(String message){
		try {
			kafkaTemplate.send("springboot-kafka",66666,message);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
}
