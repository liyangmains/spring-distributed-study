package com.ly.controller;

import javax.jms.Queue;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("send")
public class ProducerController {

	@Autowired
	private JmsMessagingTemplate jms;
	@Autowired
	private Queue queue;
	@Autowired
	private Topic topic;
	
	/**
	 * 发送生产消费点对点消息
	 * @param message
	 * @return
	 */
	@GetMapping("/queue")
	public String sendQueue(String message){
		jms.convertAndSend(queue,message);
		return "发布消费信息成功";
	}
	/**
	 * 接收consumer返回sendto节点信息
	 * @param msg
	 */
	@JmsListener(destination="out.queue")
	public void consumerMessage(String msg){
		System.out.println(msg);
	}
	/**
	 * 发送生产消费点对面订阅消息
	 * @param message
	 * @return
	 */
	@GetMapping("/topic")
	public String sendTopic(String message){
		jms.convertAndSend(topic, message);
		return "发布订阅消息成功";
	}
}
