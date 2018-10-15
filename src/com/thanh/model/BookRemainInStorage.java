package com.thanh.model;

public class BookRemainInStorage {
	private int bookId;
	private int storageId;
	private int remainQuantity;

	public BookRemainInStorage(int bookId, int storageId, int remainQuantity) {
		this.bookId = bookId;
		this.storageId = storageId;
		this.remainQuantity = remainQuantity;
	}

	public int getBookId() {
		return bookId;
	}

	public int getStorageId() {
		return storageId;
	}

	public int getRemainQuantity() {
		return remainQuantity;
	}

	@Override
	public String toString() {
		return "BookRemainInStorage [bookId=" + bookId + ", storageId=" + storageId + ", remainQuantity="
				+ remainQuantity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result + remainQuantity;
		result = prime * result + storageId;
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
		BookRemainInStorage other = (BookRemainInStorage) obj;
		if (bookId != other.bookId)
			return false;
		if (remainQuantity != other.remainQuantity)
			return false;
		if (storageId != other.storageId)
			return false;
		return true;
	}

}
