package com.thanh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="promotionEvent")
public class PromotionEvent {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int promotionEventId;
	private int promotionId;
	private int bookId;
	private int userId;
	
	public PromotionEvent() {
		
	}
	
	public PromotionEvent(int promotionEventId, int promotionId, int bookId, int userId) {
		this.promotionEventId = promotionEventId;
		this.promotionId = promotionId;
		this.bookId = bookId;
		this.userId = userId;
	}

	public PromotionEvent(int promotionId, int bookId, int userId) {
		this.promotionId = promotionId;
		this.bookId = bookId;
		this.userId = userId;
	}

	public int getPromotionEventId() {
		return promotionEventId;
	}

	public void setPromotionEventId(int promotionEventId) {
		this.promotionEventId = promotionEventId;
	}

	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
}
