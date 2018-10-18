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

import com.thanh.entity.User;
import com.thanh.enumeration.Authority;

@Repository
@Transactional
@Component("userDao")
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public User getUserByUserId(int userId) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.idEq(userId));
		User user = (User) criteria.uniqueResult();
		return user;
	}

	public int getUserIdByUsername(String username) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		User user = (User) criteria.uniqueResult();
		return user.getUserId();
	}
	
	public User getUserByUsername(String username) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		return (User) criteria.uniqueResult();
	}

	public void createUser(User user) {
		getSession().save(user);
		System.out.println("The id is generated: " + user.getUserId());
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return getSession().createQuery("from User").list();
	}

	public void updateUser(User user) {
		getSession().update(user);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUserListByOffsetQuantity(int offset, int quantity){
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.setFirstResult(offset);
		criteria.setMaxResults(quantity);
		return criteria.list();
	}

	public boolean isUsernameAvailable(String username) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		User user = (User) criteria.uniqueResult();
		return user == null;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> searchUsers(String userId, String username, String name, String authority, int offset, int quantity){
		Criteria criteria = getSession().createCriteria(User.class);
		if(!userId.equals("")) {
			criteria.add(Restrictions.eq("userId", Integer.parseInt(userId)));
		}
		if(!username.equals("")) {
			criteria.add(Restrictions.ilike("username", "%" + username + "%"));
		}
		if(!name.equals("")) {
			criteria.add(Restrictions.ilike("name", "%" + name + "%"));
		}
		if(!authority.equals("")) {
			criteria.add(Restrictions.eq("authority", Authority.valueOf(authority)));
		}
		criteria.setFirstResult(offset);
		criteria.setMaxResults(quantity);
		return criteria.list();
	}
}
