package com.thanh.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thanh.entity.Manufacturer;

@Component("manufacturerDao")
public class ManufacturerDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Manufacturer getManufacturerByManufacturerId(int manufacturerId) {
		Criteria criteria = getSession().createCriteria(Manufacturer.class);
		criteria.add(Restrictions.idEq(manufacturerId));
		return (Manufacturer) criteria.uniqueResult();
	}
}
