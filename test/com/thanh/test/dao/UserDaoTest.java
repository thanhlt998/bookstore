package com.thanh.test.dao;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thanh.dao.UserDao;
import com.thanh.entity.User;
import com.thanh.enumeration.Authority;
import com.thanh.enumeration.Gender;

@ActiveProfiles("dev")
@ContextConfiguration(value = { "classpath:com/thanh/config/dao-context.xml",
		"classpath:com/thanh/config/security-context.xml", "classpath:com/thanh/config/service-context.xml",
		"classpath:com/thanh/test/config/dataSource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static List<User> users;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		users = new ArrayList<>();
		try {
			users.add(new User(1, "tuanthanh98", "123456789", "Le Tuan Thanh", "thanhlt98@gmail.com",
						dateFormat.parse("1998-10-28"), Gender.MALE, "Hung Yen", "0101010101", Authority.ROLE_ADMIN));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetUserByUserId() {
		assertEquals(null, userDao.getUserByUserId(2));
		assertEquals(users.get(0).toString(), userDao.getUserByUserId(1).toString());
	}
	
	@Test
	public void testGetAllUsers() {
		assertEquals(users.toString(), new ArrayList<>(userDao.getAllUsers()).toString());
	}
	
	@Test
	public void testGetUserIdByUsername() {
		assertEquals(1, userDao.getUserIdByUsername("tuanthanh98"));
	}
	
	@Test
	public void testCreateUser() {
		User newUser = null;
		try {
			newUser = new User(4, "abcd", "ajdfja", "ajfjds;adfh", "fakdjfkad", dateFormat.parse("1998-09-19"), Gender.FEMALE, "adjkfjajksdj", "21212131", Authority.ROLE_USER);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//expected list
		users.add(newUser);
		userDao.createUser(newUser);
		//actual list
		List<User> actualList = new ArrayList<>(userDao.getAllUsers());
		Collections.sort(users, new UserComparator());
		Collections.sort(actualList, new UserComparator());
		
		assertEquals(users.toString(), actualList.toString());
	}
	
	@Test
	public void testIsUserNameAvailable() {
		assertEquals(false, userDao.isUsernameAvailable("tuanthanh98"));
		assertEquals(true, userDao.isUsernameAvailable("tuanthanh981"));
	}
	
	private class UserComparator implements Comparator<User>{

		@Override
		public int compare(User user1, User user2) {
			int userId1 = user1.getUserId();
			int userId2 = user2.getUserId();
			if(userId1 == userId2) {
				return 0;
			}
			else if(userId1 > userId2) {
				return 1;
			}
			else return -1;
		}
		
	}
}
