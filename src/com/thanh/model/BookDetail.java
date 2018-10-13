package com.thanh.model;

import java.util.List;

import com.thanh.entity.Book;

public class BookDetail {
	private int bookId;
	private String category;
	private String bookName;
	private String bookDescription;
	private String manufacturer;
	private String author;
	private int price;
	private List<String> imageUrls;
	private int discount;
	private int currentPrice;

	public BookDetail(Book book, String category, String manufacturer, List<String> imageUrls, int discount) {
		this.bookId = book.getBookId();
		this.category = category;
		this.bookName = book.getBookName();
		this.bookDescription = book.getBookDescription();
		this.manufacturer = manufacturer;
		this.author = book.getAuthor();
		this.price = book.getPrice();
		this.imageUrls = imageUrls;
		this.discount = discount;
		this.currentPrice = price * (100 - discount) / 100;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "BookDetail [bookId=" + bookId + ", category=" + category + ", bookName=" + bookName
				+ ", bookDescription=" + bookDescription + ", manufacturer=" + manufacturer + ", author=" + author
				+ ", price=" + price + ", imageUrls=" + imageUrls + ", discount=" + discount + "]";
	}

}
