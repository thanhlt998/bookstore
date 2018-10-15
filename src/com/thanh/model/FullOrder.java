package com.thanh.model;

import java.util.List;

import com.thanh.entity.Order;

public class FullOrder {
	private Order order;
	private List<CartItem> cartItemList;

	public FullOrder() {

	}

	public FullOrder(Order order, List<CartItem> cartItemList) {
		this.order = order;
		this.cartItemList = cartItemList;
	}

	public Order getOrder() {
		return order;
	}

	public List<CartItem> getCartItemList() {
		return cartItemList;
	}

}
