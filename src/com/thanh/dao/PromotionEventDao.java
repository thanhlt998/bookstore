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

import com.thanh.entity.PromotionEvent;

@Repository
@Transactional
@Component("promotionEventDao")
public class PromotionEventDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getAllPromotionIdByBookId(int bookId){
		Criteria criteria = getSession().createCriteria(PromotionEvent.class);
		criteria.add(Restrictions.eq("bookId", bookId));
		criteria.setProjection(Projections.property("promotionId"));
		return criteria.list();
	}
	
	public void savePromotionEvent(PromotionEvent promotionEvent) {
		getSession().save(promotionEvent);
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getBookIdListByPromotionIdOffsetQuantity(int promotionId, int offset, int quantity) {
		Criteria criteria = getSession().createCriteria(PromotionEvent.class);
		criteria.add(Restrictions.eq("promotionId", promotionId));
		criteria.setFirstResult(offset);
		criteria.setMaxResults(quantity);
		criteria.setProjection(Projections.property("bookId"));
		return criteria.list();
	}
}
