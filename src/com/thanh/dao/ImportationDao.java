package com.thanh.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
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
	
	public int countImportedBookQuantityByBookIdStorageId(int bookId, int storageId) {
		Criteria criteria = getSession().createCriteria(Importation.class);
		criteria.add(Restrictions.eq("bookId", bookId));
		criteria.add(Restrictions.eq("storageId", storageId));
		criteria.setProjection(Projections.sum("quantity"));
		if(criteria.uniqueResult() == null) return 0;
		return Integer.parseInt(criteria.uniqueResult().toString());
	}
	
	public void addImportation(Importation importation) {
		getSession().save(importation);
	}
	
	@SuppressWarnings("unchecked")
	public List<Importation> searchImportationByIdStorageIdBookIdImportDateOffsetQuantity(String importationId, String storageId, String bookId, String importDate, int offset, int quantity){
		Criteria criteria = getSession().createCriteria(Importation.class);
		if(!importationId.equals("")) {
			criteria.add(Restrictions.idEq(Integer.parseInt(importationId)));
		}
		if(!storageId.equals("")) {
			criteria.add(Restrictions.eq("storageId", Integer.parseInt(storageId)));
		}
		if(!bookId.equals("")) {
			criteria.add(Restrictions.eq("bookId", Integer.parseInt(bookId)));
		}
		if(!importDate.equals("")) {
			try {
				criteria.add(Restrictions.eq("importDate", dateFormat.parse(importDate)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		criteria.setFirstResult(offset);
		criteria.setMaxResults(quantity);
		return criteria.list();
	}
}
