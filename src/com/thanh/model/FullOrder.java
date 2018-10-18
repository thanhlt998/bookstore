package com.thanh.model;

import java.util.List;

import com.thanh.entity.Order;
import com.thanh.entity.User;

public class FullOrder {
	private User user;
	private Order order;
	private List<OrdersDetailItem> ordersDetailItemList;

	public FullOrder() {

	}

	public FullOrder(User user, Order order, List<OrdersDetailItem> ordersDetailItemList) {
		this.user = user;
		this.order = order;
		this.ordersDetailItemList = ordersDetailItemList;
	}
	
	public User getUser() {
		return user;
	}

	public Order getOrder() {
		return order;
	}

	public List<OrdersDetailItem> getOrdersDetailItemList() {
		return ordersDetailItemList;
	}

}
