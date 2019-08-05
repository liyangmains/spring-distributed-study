package com.ly.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ly.bean.Order;
import com.ly.bean.User;
import com.ly.entity.ResponseEntity;
import com.ly.service.OrderService;
import com.ly.service.UserService;

/**
 * 测试分布式事物
 * @author liY
 *
 */
@RestController
public class XATransactionController {
	
	@Autowired
	OrderService orderService;
	@Autowired
	UserService userService;
	
	@GetMapping("/add")
	@Transactional
	public ResponseEntity insertOrder(String name,BigDecimal price){
		ResponseEntity responseEntity = new ResponseEntity();
		Order order = new Order();
		order.setName(name);
		order.setPrice(price);
		orderService.insertOrder(order);
		User user = new User();
		user.setId(1);
		user.setName("谭XXXXXXX");
		userService.updateUser(user);
		if(order.getId() == null){
			responseEntity.setCode(1);
			responseEntity.setContent("添加订单失败");
		}else{
			responseEntity.setCode(0);
			responseEntity.setContent("添加订单成功");
		}
//		int i = 10 / 0;
		return responseEntity;
	}

}
