package com.thanh.dao;

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

import com.thanh.entity.Exportation;

@Repository
@Transactional
@Component("exportationDao")
public class ExportationDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Exportation> getAllExportationList() {
		Criteria criteria = getSession().createCriteria(Exportation.class);
		return criteria.list();
	}
	
	public int countNoBookExportedByBookId(int bookId) {
		Criteria criteria = getSession().createCriteria(Exportation.class);
		criteria.add(Restrictions.eq("bookId", bookId));
		criteria.setProjection(Projections.sum("quantity"));
		if(criteria.uniqueResult() == null) return 0;
		return Integer.parseInt(criteria.uniqueResult().toString());
	}
	
	public void addExportationList(List<Exportation> exportationList) {
		Session session = getSession();
		for(Exportation exportation: exportationList) {
			session.save(exportation);
		}
	}
	
	public boolean addExportation(Exportation exportation) {
		getSession().save(exportation);
		return getSession().getTransaction().wasRolledBack();
	}
	
	public int countExportedBookQuantityByBookIdStorageId(int bookId, int storageId) {
		Criteria criteria = getSession().createCriteria(Exportation.class);
		criteria.add(Restrictions.eq("storageId", storageId));
		criteria.add(Restrictions.eq("bookId", bookId));
		criteria.setProjection(Projections.sum("quantity"));
		if(criteria.uniqueResult() == null) return 0;
		return Integer.parseInt(criteria.uniqueResult().toString());
	}
	
}
