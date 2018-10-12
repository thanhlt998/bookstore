package com.thanh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="storage")
public class Storage {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int storageId;
	private String storageName;
	private String storageAddress;
	private int stockKeeperId;
	
	public Storage() {
		
	}

	public Storage(int storageId, String storageName, String storageAddress, int stockKeeperId) {
		this.storageId = storageId;
		this.storageName = storageName;
		this.storageAddress = storageAddress;
		this.stockKeeperId = stockKeeperId;
	}

	public Storage(String storageName, String storageAddress, int stockKeeperId) {
		super();
		this.storageName = storageName;
		this.storageAddress = storageAddress;
		this.stockKeeperId = stockKeeperId;
	}

	public int getStorageId() {
		return storageId;
	}

	public void setStorageId(int storageId) {
		this.storageId = storageId;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public String getStorageAddress() {
		return storageAddress;
	}

	public void setStorageAddress(String storageAddress) {
		this.storageAddress = storageAddress;
	}

	public int getStockKeeperId() {
		return stockKeeperId;
	}

	public void setStockKeeperId(int stockKeeperId) {
		this.stockKeeperId = stockKeeperId;
	}

}
