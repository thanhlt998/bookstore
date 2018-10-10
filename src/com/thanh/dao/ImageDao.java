package com.thanh.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("imageDao")
class ImageDao {
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setJdbc(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<String> getImagesByBookId(int bookId) {
		MapSqlParameterSource params = new MapSqlParameterSource("bookId", bookId);
		String sql = "select imageUrl from image where bookId = :bookId";
		return jdbc.queryForList(sql, params, String.class);
	}
	
	public String getAnImageByBookId(int bookId) {
		MapSqlParameterSource params = new MapSqlParameterSource("bookId", bookId);
		String sql = "select imageUrl from image where bookId = :bookId limit 1";
		return jdbc.queryForObject(sql, params, String.class);
	}
}
