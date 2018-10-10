package com.thanh.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.thanh.entity.Book;
import com.thanh.model.BookListView;

@Component("bookDao")
public class BookDao {
	
private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setJdbc(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Book> getBooks(int limit, int offset) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		String sql = "select * from book limit :limit, :offset";
		
		params.addValue("limit", limit);
		params.addValue("offset", offset);
		
		return jdbc.query(sql, params, new BeanPropertyRowMapper<>(Book.class));
	}
	
//	public List<BookListView> getBookListViewByBookId(int limit, int offset) {
//		List<Book> books = getBooks(limit, offset);
//		
//	}
}
