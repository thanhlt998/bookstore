package com.thanh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="image")
public class Image {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int imageId;
	private int bookId;
	private String imageUrl;
	
	public Image() {
		
	}

	public Image(int imageId, int bookId, String imageUrl) {
		this.imageId = imageId;
		this.bookId = bookId;
		this.imageUrl = imageUrl;
	}

	public Image(int bookId, String imageUrl) {
		super();
		this.bookId = bookId;
		this.imageUrl = imageUrl;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	
}
