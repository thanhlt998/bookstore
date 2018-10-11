package com.thanh.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="exportation")
public class Exportation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int exportationId;
	private int storageId;
	private int bookId;
	private int quantity;
	private Date exportDate;

	public Exportation(int exportationId, int storageId, int bookId, int quantity, Date exportDate) {
		this.exportationId = exportationId;
		this.storageId = storageId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.exportDate = exportDate;
	}

	public Exportation(int storageId, int bookId, int quantity, Date exportDate) {
		this.storageId = storageId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.exportDate = exportDate;
	}

	public int getExportationId() {
		return exportationId;
	}

	public void setExportationId(int exportationId) {
		this.exportationId = exportationId;
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

	public Date getExportDate() {
		return exportDate;
	}

	public void setExportDate(Date exportDate) {
		this.exportDate = exportDate;
	}

}
