package com.thanh.entity;

public class OrderDetailPK {
	private int orderId;
	private int bookId;

	public OrderDetailPK() {

	}

	public OrderDetailPK(int orderId, int bookId) {
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
