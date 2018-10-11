package com.thanh.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thanh.entity.Image;

@Repository
@Transactional
@Component("imageDao")
public class ImageDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getImageUrlsByBookId(int bookId) {
		Query query = getSession().createQuery("select imageUrl from image where bookId = :bookId");
		query.setParameter("bookId", bookId);
		return query.list();
	}
	
	public String getAnImageUrlByBookId(int bookId) {
		Criteria criteria = getSession().createCriteria(Image.class);
		criteria.add(Restrictions.eq("bookId", bookId));
		criteria.setMaxResults(1);
		
		List<Image> results = criteria.list();
		if(results.isEmpty()) {
			return null;
		}
		return results.get(0).getImageUrl();
	}
}
