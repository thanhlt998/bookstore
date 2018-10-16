package com.thanh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanh.dao.UserDao;
import com.thanh.entity.User;

@Service("userService")
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public boolean checkAvailableUsername(String username) {
		return userDao.isUsernameAvailable(username);
	}
	
	public void createUser(User user) {
		userDao.createUser(user);
	}
	
	public int getUserIdByUsername(String username) {
		return userDao.getUserIdByUsername(username);
	}
	
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}
	
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
}
