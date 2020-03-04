package com.ly.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ly.bean.Order;
import com.ly.entity.ResponseEntity;
import com.ly.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@GetMapping("/add")
	public ResponseEntity insertOrder(String name,BigDecimal price){
		ResponseEntity responseEntity = new ResponseEntity();
		Order order = new Order();
		order.setName(name);
		order.setPrice(price);
		orderService.insertOrder(order);
		if(order.getId() == null){
			responseEntity.setCode(1);
			responseEntity.setContent("添加订单失败");
		}else{
			responseEntity.setCode(0);
			responseEntity.setContent("添加订单成功");
		}
		return responseEntity;
	}
	@GetMapping("/edit")
	public ResponseEntity updateOrder(Integer id ,String name,BigDecimal price){
		ResponseEntity responseEntity = new ResponseEntity();
		Order order = new Order();
		order.setId(id);
		order.setName(name);
		order.setPrice(price);
		if(order.getId() != null){
			int num = orderService.updateOrder(order);
//			int num = orderService.transactionUpdateOrder(order);
			if(num <= 0){
				responseEntity.setCode(1);
				responseEntity.setContent("更新订单失败");	
				return responseEntity;
			}
		}else{
			responseEntity.setCode(1);
			responseEntity.setContent("订单编号不能为空");
			return responseEntity;
		}
		responseEntity.setCode(0);
		responseEntity.setContent("更新订单成功");
		return responseEntity;
	}
	
}
