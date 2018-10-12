package com.thanh.test.dao;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thanh.dao.UserDao;
import com.thanh.entity.User;
import com.thanh.enumeration.Authority;
import com.thanh.enumeration.Gender;

import beforetest.GenerateDataTest;

@ActiveProfiles("dev")
@ContextConfiguration(value = { "classpath:com/thanh/config/dao-context.xml",
		"classpath:com/thanh/config/security-context.xml", "classpath:com/thanh/config/service-context.xml",
		"classpath:com/thanh/test/config/dataSource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		GenerateDataTest.generateData(jdbc);
	}

	@Test
	public void testGetUserByUserId() {
		User user = GenerateDataTest.users.get(new Random().nextInt(10));
		assertEquals(user, userDao.getUserByUserId(user.getUserId()));
	}
	
	@Test
	public void testGetAllUsers() {
		List<User> result = userDao.getAllUsers();
		
		Collections.sort(GenerateDataTest.users, new UserComparator());
		Collections.sort(result, new UserComparator());
		
		assertEquals(GenerateDataTest.users.toString(), result.toString());
	}
	
	@Test
	public void testGetUserIdByUsername() {
		User user = GenerateDataTest.users.get(new Random().nextInt(10));
		assertEquals(user.getUserId(), userDao.getUserIdByUsername(user.getUsername()));
	}
	
	@Test
	public void testCreateUser() {
		User newUser = null;
		try {
			newUser = new User(101, "abcd", "ajdfja", "ajfjds;adfh", "fakdjfkad", dateFormat.parse("1998-09-19"), Gender.FEMALE, "adjkfjajksdj", "21212131", Authority.ROLE_USER);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<User> users = new ArrayList<>(GenerateDataTest.users);
		users.add(newUser);
		userDao.createUser(newUser);
		
		List<User> result = userDao.getAllUsers();
		
		Collections.sort(users, new UserComparator());
		Collections.sort(result, new UserComparator());
		
		assertEquals(users.toString(), result.toString());
		
	}
	
	@Test
	public void testIsUserNameAvailable() {
		assertEquals(false, userDao.isUsernameAvailable(GenerateDataTest.users.get(new Random().nextInt(10)).getUsername()));
		assertEquals(true, userDao.isUsernameAvailable("adsfjadjjadkjfj"));
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
