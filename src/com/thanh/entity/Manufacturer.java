package com.thanh.entity;

public class Manufacturer {
	private int manufacturerId;
	private String manufacturerName;

	public Manufacturer(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public Manufacturer(int manufacturerId, String manufacturerName) {
		this.manufacturerId = manufacturerId;
		this.manufacturerName = manufacturerName;
	}
	
	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	
	public int getManufacturerId() {
		return manufacturerId;
	}
	
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	
	public String getManufacturerName() {
		return manufacturerName;
	}
}
