package com.thanh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int manufacturerId;
	private String manufacturerName;

	public Manufacturer() {

	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + manufacturerId;
		result = prime * result + ((manufacturerName == null) ? 0 : manufacturerName.hashCode());
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
		Manufacturer other = (Manufacturer) obj;
		if (manufacturerId != other.manufacturerId)
			return false;
		if (manufacturerName == null) {
			if (other.manufacturerName != null)
				return false;
		} else if (!manufacturerName.equals(other.manufacturerName))
			return false;
		return true;
	}

}
