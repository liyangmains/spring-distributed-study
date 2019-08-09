package com.ly.pubsub;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
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
public class ActiveMqTopicReceiver {

	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.16.189:61616");
		Connection connection = null;
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			
			Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createTopic("first-Topic");
			MessageConsumer messageConsumer = session.createConsumer(destination);
			TextMessage textMessage = (TextMessage)messageConsumer.receive();
			System.out.println(textMessage.getText());
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
