package com.thanh.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="importation")
public class Importation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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

}
