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
	
	public Exportation() {
		
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result + ((exportDate == null) ? 0 : exportDate.hashCode());
		result = prime * result + exportationId;
		result = prime * result + quantity;
		result = prime * result + storageId;
		return result;
	}

	@Override
	public String toString() {
		return "Exportation [exportationId=" + exportationId + ", storageId=" + storageId + ", bookId=" + bookId
				+ ", quantity=" + quantity + ", exportDate=" + exportDate + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exportation other = (Exportation) obj;
		if (bookId != other.bookId)
			return false;
		if (exportDate == null) {
			if (other.exportDate != null)
				return false;
		} else if (!exportDate.equals(other.exportDate))
			return false;
		if (exportationId != other.exportationId)
			return false;
		if (quantity != other.quantity)
			return false;
		if (storageId != other.storageId)
			return false;
		return true;
	}

}
