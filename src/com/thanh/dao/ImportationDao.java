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

import com.thanh.entity.Importation;

@Repository
@Transactional
@Component("importationDao")
public class ImportationDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public int countNoBookImportedByBookId(int bookId) {
		Criteria criteria = getSession().createCriteria(Importation.class);
		criteria.add(Restrictions.eq("bookId", bookId));
		criteria.setProjection(Projections.sum("quantity"));
		if(criteria.uniqueResult() == null) return 0;
		return Integer.parseInt(criteria.uniqueResult().toString());
	}
}