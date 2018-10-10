package com.thanh.entity;

public class Book {
	private int bookId;
	private int categoryId;
	private String bookName;
	private String bookDescription;
	private int manufacturerId;
	private String author;
	private int price;

	public Book() {
		
	}

	public Book(int bookId, int categoryId, String bookName, String bookDescription, int manufacturerId, String author,
			int price) {
		this.bookId = bookId;
		this.categoryId = categoryId;
		this.bookName = bookName;
		this.bookDescription = bookDescription;
		this.manufacturerId = manufacturerId;
		this.author = author;
		this.price = price;
	}

	public Book(int categoryId, String bookName, String bookDescription, int manufacturerId, String author, int price) {
		this.categoryId = categoryId;
		this.bookName = bookName;
		this.bookDescription = bookDescription;
		this.manufacturerId = manufacturerId;
		this.author = author;
		this.price = price;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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

	public int getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((bookDescription == null) ? 0 : bookDescription.hashCode());
		result = prime * result + bookId;
		result = prime * result + ((bookName == null) ? 0 : bookName.hashCode());
		result = prime * result + categoryId;
		result = prime * result + manufacturerId;
		result = prime * result + price;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookDescription == null) {
			if (other.bookDescription != null)
				return false;
		} else if (!bookDescription.equals(other.bookDescription))
			return false;
		if (bookId != other.bookId)
			return false;
		if (bookName == null) {
			if (other.bookName != null)
				return false;
		} else if (!bookName.equals(other.bookName))
			return false;
		if (categoryId != other.categoryId)
			return false;
		if (manufacturerId != other.manufacturerId)
			return false;
		if (price != other.price)
			return false;
		return true;
	}

}
