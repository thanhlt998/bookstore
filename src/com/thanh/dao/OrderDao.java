package com.thanh.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thanh.entity.Order;

@Repository
@Transactional
@Component("orderDao")
public class OrderDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public int addOrder(Order order) {
		getSession().save(order);
		return order.getOrderId();
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getOrderListByUserIdOffsetQuantity(int userId, int offset, int quantity){
		Criteria criteria = getSession().createCriteria(Order.class);
		criteria.add(Restrictions.eq("userId", userId));
		criteria.addOrder(org.hibernate.criterion.Order.desc("orderDate"));
		criteria.setFirstResult(offset);
		criteria.setMaxResults(quantity);
		return criteria.list();
	}
}
