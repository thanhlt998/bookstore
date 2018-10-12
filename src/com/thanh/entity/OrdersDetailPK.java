package com.thanh.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OrdersDetailPK implements Serializable{
	private int orderId;
	private int bookId;

	public OrdersDetailPK() {

	}

	public OrdersDetailPK(int orderId, int bookId) {
		this.orderId = orderId;
		this.bookId = bookId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

}
