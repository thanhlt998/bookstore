package com.thanh.model;

import com.thanh.entity.OrdersDetail;

public class OrdersDetailItem {
	private String bookName;
	private OrdersDetail ordersDetail;

	public OrdersDetailItem(String bookName, OrdersDetail ordersDetail) {
		this.bookName = bookName;
		this.ordersDetail = ordersDetail;
	}

	public String getBookName() {
		return bookName;
	}

	public OrdersDetail getOrdersDetail() {
		return ordersDetail;
	}

}
