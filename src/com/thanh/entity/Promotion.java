package com.thanh.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "promotion")
public class Promotion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int promotionId;
	private String promotionDescription;
	private Date fromDate;
	private Date toDate;
	private int discount;
	
	public Promotion() {
		
	}

	public Promotion(int promotionId, String promotionDescription, Date fromDate, Date toDate, int discount) {
		this.promotionId = promotionId;
		this.promotionDescription = promotionDescription;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.discount = discount;
	}

	public Promotion(String promotionDescription, Date fromDate, Date toDate, int discount) {
		this.promotionDescription = promotionDescription;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.discount = discount;
	}

	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	public String getPromotionDescription() {
		return promotionDescription;
	}

	public void setPromotionDescription(String promotionDescription) {
		this.promotionDescription = promotionDescription;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + discount;
		result = prime * result + ((fromDate == null) ? 0 : fromDate.hashCode());
		result = prime * result + ((promotionDescription == null) ? 0 : promotionDescription.hashCode());
		result = prime * result + promotionId;
		result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
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
		Promotion other = (Promotion) obj;
		if (discount != other.discount)
			return false;
		if (fromDate == null) {
			if (other.fromDate != null)
				return false;
		} else if (!fromDate.equals(other.fromDate))
			return false;
		if (promotionDescription == null) {
			if (other.promotionDescription != null)
				return false;
		} else if (!promotionDescription.equals(other.promotionDescription))
			return false;
		if (promotionId != other.promotionId)
			return false;
		if (toDate == null) {
			if (other.toDate != null)
				return false;
		} else if (!toDate.equals(other.toDate))
			return false;
		return true;
	}

}
