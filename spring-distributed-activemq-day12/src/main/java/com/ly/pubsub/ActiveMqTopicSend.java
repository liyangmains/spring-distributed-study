package com.ly.pubsub;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 发布订阅
 * 每个消息可以有多个消费者
 * 消息的生产者和消费者之间存在时间上的相关性，订阅一个主题的消费者只能消费自它订阅之后发布的消息(消费端需先订阅才会收到发送端消息)。JMS规范允许提供客户端创建持久订阅
 * @author Administrator
 *
 */
public class ActiveMqTopicSend {

	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.16.189:61616");
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
			Destination destination = session.createTopic("first-Topic");
			//创建消息发送者
			MessageProducer producer = session.createProducer(destination);
			//编写信息
			TextMessage textMessage = session.createTextMessage("6666666688888888999999999");
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
