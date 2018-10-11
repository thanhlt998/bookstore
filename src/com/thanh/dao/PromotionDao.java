package com.thanh.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thanh.entity.Promotion;

@Repository
@Transactional
@Component("promotionDao")
public class PromotionDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Promotion getPromotionByPromotionId(int promotionId) {
		Criteria criteria = getSession().createCriteria(Promotion.class);
		criteria.add(Restrictions.idEq(promotionId));
		return (Promotion) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Promotion> getAllCurrentPromotion(List<Integer> promotionIdList){
		Date today = Calendar.getInstance().getTime();
		Criteria criteria = getSession().createCriteria(Promotion.class);
		criteria.add(Restrictions.lt("fromDate", today));
		criteria.add(Restrictions.gt("toDate", today));
		return criteria.list();
	}
}
