package com.thanh.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thanh.enumeration.OrderStatus;
import com.thanh.enumeration.PaymentMethod;

@Entity
@Table(name="order")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int orderId;
	private int userId;
	private Date orderDate;
	private Date shipDate;
	private String shipAddress;
	private int totalAmount;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	public Order() {
		
	}

	public Order(int orderId, int userId, Date orderDate, Date shipDate, String shipAddress, int totalAmount,
			PaymentMethod paymentMethod, OrderStatus status) {
		this.orderId = orderId;
		this.userId = userId;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.shipAddress = shipAddress;
		this.totalAmount = totalAmount;
		this.paymentMethod = paymentMethod;
		this.status = status;
	}

	public Order(int userId, Date orderDate, Date shipDate, String shipAddress, int totalAmount,
			PaymentMethod paymentMethod, OrderStatus status) {
		this.userId = userId;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.shipAddress = shipAddress;
		this.totalAmount = totalAmount;
		this.paymentMethod = paymentMethod;
		this.status = status;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

}
