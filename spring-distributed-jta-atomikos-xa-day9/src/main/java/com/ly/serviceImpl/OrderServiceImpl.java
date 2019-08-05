package com.ly.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ly.annotation.TargetDataSource;
import com.ly.bean.Order;
import com.ly.constant.Data;
import com.ly.dao.OrderMapper;
import com.ly.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderMapper orderMapper;
	
	@Override
	@TargetDataSource(name=Data.DATASOURCE2)
	public Order insertOrder(Order order) {
		// TODO Auto-generated method stub
		orderMapper.insert(order);
		return order;
	}

	@Override
	@TargetDataSource(name=Data.DATASOURCE2)
	public int updateOrder(Order order) {
		// TODO Auto-generated method stub
		return orderMapper.updateOrder(order);
	}

	/**
	 * 测试单数据源事物
	 */
	@Override
	@TargetDataSource(name=Data.DATASOURCE2)
	@Transactional
	public int transactionUpdateOrder(Order order) {
		// TODO Auto-generated method stub
		int num = orderMapper.updateOrder(order);
		int i = 10/0;
		return 0;
	}


}
