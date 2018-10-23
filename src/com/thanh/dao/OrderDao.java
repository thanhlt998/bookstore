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
import com.thanh.enumeration.OrderStatus;

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
	
	public Order getOrderByOrderId(int orderId) {
		Criteria criteria = getSession().createCriteria(Order.class);
		criteria.add(Restrictions.idEq(orderId));
		return (Order) criteria.uniqueResult();
	}
	
	public boolean checkExistOrderByUserIdOrderId(int userId, int orderId) {
		Criteria criteria = getSession().createCriteria(Order.class);
		criteria.add(Restrictions.idEq(orderId));
		criteria.add(Restrictions.eq("userId", userId));
		return criteria.uniqueResult() != null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> searchOrderList(String orderId, String userId, String orderStatus, int offset, int quantity){
		Criteria criteria = getSession().createCriteria(Order.class);
		if(!orderId.equals("")) {
			criteria.add(Restrictions.idEq(Integer.parseInt(orderId)));
		}
		if(!userId.equals("")) {
			criteria.add(Restrictions.eq("userId", Integer.parseInt(userId)));
		}
		if(!orderStatus.equals("")) {
			criteria.add(Restrictions.eq("status", OrderStatus.valueOf(orderStatus)));
		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("orderDate"));
		criteria.setFirstResult(offset);
		criteria.setMaxResults(quantity);
		return criteria.list();
	}
}
