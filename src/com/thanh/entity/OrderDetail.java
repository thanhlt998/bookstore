package com.thanh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="orderDetail")
@IdClass(value=OrderDetailPK.class)
public class OrderDetail {
	@Id
	private int orderId;
	@Id
	private int bookId;
	private int quantity;
	private int pricePerUnit;

	public OrderDetail(int orderId, int bookId, int quantity, int pricePerUnit) {
		this.orderId = orderId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(int pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

}
