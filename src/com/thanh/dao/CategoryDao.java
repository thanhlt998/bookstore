package com.thanh.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thanh.entity.Category;

@Repository
@Transactional
@Component("categoryDao")
public class CategoryDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Category getCategoryByCategoryId(int categoryId) {
		Criteria criteria = getSession().createCriteria(Category.class);
		criteria.add(Restrictions.eq("categoryId", categoryId));
		return (Category) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> getAllCategoriesOrderByCategoryName(){
		Criteria criteria = getSession().createCriteria(Category.class);
		criteria.addOrder(Order.asc("categoryName"));
		return criteria.list();
	}
	
	public String getCategoryNameByCategoryId(int categoryId) {
		Criteria criteria = getSession().createCriteria(Category.class);
		criteria.add(Restrictions.idEq(categoryId));
		criteria.setProjection(Projections.property("categoryName"));
		return (String) criteria.uniqueResult();
	}
	
}
