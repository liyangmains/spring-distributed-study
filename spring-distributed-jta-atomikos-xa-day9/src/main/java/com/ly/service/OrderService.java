package com.ly.service;

import com.ly.bean.Order;

public interface OrderService {

	Order insertOrder(Order order);
	
	int updateOrder(Order order);
	
	int transactionUpdateOrder(Order order);
}
