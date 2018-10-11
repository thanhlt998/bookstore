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

import com.thanh.entity.Book;

@Repository
@Transactional
@Component("bookDao")
public class BookDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Book getBookByBookId(int bookId) {
		Criteria criteria = getSession().createCriteria(Book.class);
		criteria.add(Restrictions.idEq(bookId));
		return (Book) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> getBookListByOffsetQuantity(int offset, int quantity){
		Criteria criteria = getSession().createCriteria(Book.class);
		criteria.setFirstResult(offset);
		criteria.setMaxResults(quantity);
		return criteria.list();
	}
	
}
