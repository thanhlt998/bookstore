package com.thanh.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "importation")
public class Importation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int importationId;
	private int storageId;
	private int bookId;
	private int quantity;
	private Date importDate;
	private int importPrice;

	public Importation() {

	}

	public Importation(int importationId, int storageId, int bookId, int quantity, Date importDate, int importPrice) {
		this.importationId = importationId;
		this.storageId = storageId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.importDate = importDate;
		this.importPrice = importPrice;
	}

	public Importation(int storageId, int bookId, int quantity, Date importDate, int importPrice) {
		this.storageId = storageId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.importDate = importDate;
		this.importPrice = importPrice;
	}

	public int getImportationId() {
		return importationId;
	}

	public void setImportationId(int importationId) {
		this.importationId = importationId;
	}

	public int getStorageId() {
		return storageId;
	}

	public void setStorageId(int storageId) {
		this.storageId = storageId;
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

	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public int getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(int importPrice) {
		this.importPrice = importPrice;
	}

	@Override
	public String toString() {
		return "Importation [importationId=" + importationId + ", storageId=" + storageId + ", bookId=" + bookId
				+ ", quantity=" + quantity + ", importDate=" + importDate + ", importPrice=" + importPrice + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result + ((importDate == null) ? 0 : importDate.hashCode());
		result = prime * result + importPrice;
		result = prime * result + importationId;
		result = prime * result + quantity;
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
		Importation other = (Importation) obj;
		if (bookId != other.bookId)
			return false;
		if (importDate == null) {
			if (other.importDate != null)
				return false;
		} else if (!importDate.equals(other.importDate))
			return false;
		if (importPrice != other.importPrice)
			return false;
		if (importationId != other.importationId)
			return false;
		if (quantity != other.quantity)
			return false;
		if (storageId != other.storageId)
			return false;
		return true;
	}

}
