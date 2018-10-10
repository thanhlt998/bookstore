package com.thanh.entity;

import java.util.Date;

public class Promotion {
	private int promotionId;
	private String promotionDescription;
	private Date fromDate;
	private Date toDate;
	private int discount;

	public Promotion(int promotionId, String promotionDescription, Date fromDate, Date toDate, int discount) {
		this.promotionId = promotionId;
		this.promotionDescription = promotionDescription;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.discount = discount;
	}

	public Promotion(String promotionDescription, Date fromDate, Date toDate, int discount) {
		this.promotionDescription = promotionDescription;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.discount = discount;
	}

	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	public String getPromotionDescription() {
		return promotionDescription;
	}

	public void setPromotionDescription(String promotionDescription) {
		this.promotionDescription = promotionDescription;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

}
