package com.ly.listeners;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TopicListener {

	@JmsListener(destination="springboot-topic",containerFactory="jmsListenerContainerTopic")
	public void getQueue(String message){
		System.out.println("consumer收到producer(topic)信息:"+message);
	}
}
