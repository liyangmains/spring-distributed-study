package com.ly.broker;

import org.apache.activemq.broker.BrokerService;

public class broker {

	public static void main(String[] args) {
		BrokerService service = new BrokerService();
		service.setUseJmx(true);
		try {
			service.addConnector("tcp://localhost:61616");
			service.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
