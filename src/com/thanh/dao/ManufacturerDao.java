package com.thanh.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thanh.entity.Manufacturer;

@Repository
@Transactional
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
	
	public String getManufacturerNameByManufacturerId(int manufacturerId) {
		Criteria criteria = getSession().createCriteria(Manufacturer.class);
		criteria.add(Restrictions.idEq(manufacturerId));
		criteria.setProjection(Projections.property("manufacturerName"));
		return (String) criteria.uniqueResult();
	}
}
