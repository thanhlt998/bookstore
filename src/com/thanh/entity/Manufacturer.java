package com.thanh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="manufacturer")
public class Manufacturer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
