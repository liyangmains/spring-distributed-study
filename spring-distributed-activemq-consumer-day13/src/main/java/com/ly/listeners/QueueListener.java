package com.ly.listeners;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class QueueListener {

	/**
	 * 接收queue节点点对点消息并sendto返回消息给out.queue节点
	 * @param message
	 * @return
	 */
	@JmsListener(destination="springboot-queue",containerFactory="jmsListenerContainerQueue")
	@SendTo("out.queue")
	public String getQueue(String message){
		System.out.println("consumer收到producer(queue)信息:"+message);
		return "consumer(queue)返回:"+message;
	}
}
