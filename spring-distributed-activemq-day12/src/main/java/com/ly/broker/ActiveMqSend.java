package com.ly.broker;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 点对点
 * 每个消息只能有一个消费者
 * 消息的产生和消费者之间没有时间上的相关性。无论消费者在生产者发送消息的时候是否处于运行状态，都可以提取消息
 * @author liY
 *
 */
public class ActiveMqSend {

	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = null;
		try {
			//创建连接
			connection = connectionFactory.createConnection();
			connection.start();
			/**
			 * 创建会话
			 * (Boolean.TRUE, Session.AUTO_ACKNOWLEDGE)(事务性会话,自动确认消息)需发送端消费端手动确认消费消息session.commit()
			 * (Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE)(非事务性会话,客户端确认消费)不需session.commit(),需消费端textMessage.acknowledge()确认消费信息
			 * (Boolean.FALSE, Session.DUPS_OK_ACKNOWLEDGE)(非事务性会话,延迟确认模式)
			 */
			Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			//创建消息队列（如果队列已存在不会创建，first-queue是队列名称）
			//destination表示目的地
			Destination destination = session.createQueue("first-queue");
			//创建消息发送者
			MessageProducer producer = session.createProducer(destination);
			//编写信息
			TextMessage textMessage = session.createTextMessage("666666666666666666666666666666666666666666");
			//发送信息
			producer.send(textMessage);
			session.commit();
			session.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(connection != null){
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
