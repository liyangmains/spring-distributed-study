package com.ly.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ly.bean.Order;

@Mapper
public interface OrderMapper {

	void insert(Order order);
	
	int updateOrder(Order order);
}
