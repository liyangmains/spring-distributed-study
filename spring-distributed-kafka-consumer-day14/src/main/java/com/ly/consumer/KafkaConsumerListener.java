package com.ly.consumer;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {
	
	private Logger log = LoggerFactory.getLogger(KafkaConsumerListener.class);

	@KafkaListener(topics="springboot-kafka")
	public void messageListener(ConsumerRecord<?, ?> record){
		log.info("主题:{} key:{} value:{}",record.topic(),record.key(),record.value());
	}
}
