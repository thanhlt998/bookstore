package com.thanh.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thanh.entity.User;

@Transactional
@Component("userDao")
public class UserDao {
	
	private NamedParameterJdbcTemplate jdbc;
	
	private static RowMapper<User> userRowMapper = new BeanPropertyRowMapper<>(User.class);
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	@Autowired
	public void setJdbc(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public User getUserByUserId(int userId) {
		MapSqlParameterSource params = new MapSqlParameterSource("userId", userId);
		String sql = "select * from user where userId = :userId";
		
		List<User> users = jdbc.query(sql, params, userRowMapper);
		if(users.isEmpty()) {
			return null;
		}
		else {
			return users.get(0);
		}
	}
	
	public int getUserIdByUsername(String username) {
		MapSqlParameterSource params = new MapSqlParameterSource("username", username);
		String sql = "select userId from user where username = :username";
		
		return jdbc.queryForObject(sql, params, Integer.class);
	}
	
	public void createUser(User user) {
		/*MapSqlParameterSource params = new MapSqlParameterSource();
		String sql = "insert into user (username, password, name, email, birthDate, gender, address, phone, authority) "
				+ "values (:username, :password, :name, :email, :birthDate, :gender, :address, :phone, :authority)";
		params.addValue("username", user.getUsername());
		params.addValue("password", user.getPassword());
		params.addValue("name", user.getName());
		params.addValue("email", user.getEmail());
		params.addValue("birthDate", dateFormat.format(user.getBirthDate()));
		params.addValue("gender", user.getGender().name());
		params.addValue("address", user.getAddress());
		params.addValue("phone", user.getPhone());
		params.addValue("authority", user.getAuthority().name());
		
		return jdbc.update(sql, params) == 1;*/
		getSession().save(user);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers(){
		return getSession().createQuery("from User").list();
	}
	
	public boolean updateUser(User user) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		String sql = "update user set password = :password, name = :name, email = :email, birthDate = :birthDate, "
				+ "gender = :gender, address = :address, phone = :phone, authority = :authority "
				+ "where userId = :userId";
		
		params.addValue("userId", user.getUserId());
		params.addValue("username", user.getUsername());
		params.addValue("password", user.getPassword());
		params.addValue("name", user.getName());
		params.addValue("email", user.getEmail());
		params.addValue("birthDate", dateFormat.format(user.getBirthDate()));
		params.addValue("gender", user.getGender().name());
		params.addValue("address", user.getAddress());
		params.addValue("phone", user.getPhone());
		params.addValue("authority", user.getAuthority().name());
		
		return jdbc.update(sql, params) == 1;
	}
	
	public boolean isUsernameAvailable(String username) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);
		String sql = "select count(*) from user where username = :username";
		
		return jdbc.queryForObject(sql, params, Integer.class) == 0;
	}
}
