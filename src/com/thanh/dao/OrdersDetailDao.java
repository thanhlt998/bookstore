package com.thanh.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thanh.entity.OrdersDetail;

@Repository
@Transactional
@Component("ordersDetailDao")
public class OrdersDetailDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<OrdersDetail> getOrdersDetailListByOrderId(int orderId){
		Criteria criteria = getSession().createCriteria(OrdersDetail.class);
		criteria.add(Restrictions.eq("orderId", orderId));
		return criteria.list();
	}
	
	public void addOrdersDetailList(List<OrdersDetail> ordersDetailList) {
		Session session = getSession();
		for(OrdersDetail ordersDetail: ordersDetailList) {
			session.save(ordersDetail);
		}
	}
}
