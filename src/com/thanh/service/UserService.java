package com.thanh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanh.dao.UserDao;
import com.thanh.entity.User;

@Service("userService")
public class UserService {
	private static final int NO_PEOPLE_IN_PAGE = 10;
	
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
	
	public User getUserByUserId(int userId) {
		return userDao.getUserByUserId(userId);
	}
	
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	
	public List<User> getUserListByPage(int page){
		return userDao.getUserListByOffsetQuantity(page * NO_PEOPLE_IN_PAGE, NO_PEOPLE_IN_PAGE);
	}
	
	public List<User> searchUserListByPage(String userId, String username, String name, String authority, int page){
		return userDao.searchUsers(userId, username, name, authority, page * NO_PEOPLE_IN_PAGE, NO_PEOPLE_IN_PAGE);
	}
}
