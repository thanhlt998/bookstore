package com.thanh.model;

public class CartItem {
	private BookListView book;
	private int quantity;
	
	public CartItem() {
		
	}

	public CartItem(BookListView book, int quantity) {
		this.book = book;
		this.quantity = quantity;
	}

	public BookListView getBook() {
		return book;
	}

	public void setBook(BookListView book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void increaseQuantity(int addQuantity) {
		this.quantity += addQuantity;
	}
	
}
