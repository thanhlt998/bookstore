package com.thanh.model;

public class BookListView {
	private int bookId;
	private String bookName;
	private String imageUrl;
	private int price;
	private int discount;

	public BookListView(int bookId, String bookName, String imageUrl, int price, int discount) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.imageUrl = imageUrl;
		this.price = price;
		this.discount = discount;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

}
