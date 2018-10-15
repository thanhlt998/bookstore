package com.thanh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanh.dao.OrderCreationDao;
import com.thanh.entity.Order;
import com.thanh.model.CartItem;

@Service("orderService")
public class OrderService {
	@Autowired
	private OrderCreationDao orderCreationDao;
	
	public boolean createOrder(Order order, List<CartItem> cartItemList, List<Integer> storageIdList) {
		return orderCreationDao.order(order, cartItemList, storageIdList);
	}
}
