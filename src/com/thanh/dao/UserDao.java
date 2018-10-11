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

	public void createUser(User user) {
		getSession().save(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return getSession().createQuery("from User").list();
	}

	public void updateUser(User user) {
		getSession().update(user);
	}

	public boolean isUsernameAvailable(String username) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		User user = (User) criteria.uniqueResult();
		return user == null;
	}
}
