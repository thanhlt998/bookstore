package com.thanh.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.thanh.entity.Promotion;

@Component("promotionDao")
public class PromotionDao {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setJdbc(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	private List<Integer> getCurrentDiscountByBookId(int bookId) {
		Date now = Calendar.getInstance().getTime();

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("bookId", bookId);
		params.addValue("now", dateFormat.format(now));

		String sql = "select discount from promotion, promotionEvent where promotionEvent.bookId = :bookId and"
				+ " promotion.promotionId = promotionEvent.promotionId and (:now between promotion.fromDate and promotion.toDate)";
		return jdbc.queryForList(sql, params, Integer.class);
	}

	private List<Promotion> getCurrentPromotionByBookId(int bookId) {
		Date now = Calendar.getInstance().getTime();

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("bookId", bookId);
		params.addValue("now", dateFormat.format(now));

		String sql = "select promotion.promotionId, promotionDescription, fromDate, toDate, discount from promotion, promotionEvent where promotionEvent.bookId = :bookId and"
				+ " promotion.promotionId = promotionEvent.promotionId and (:now between promotion.fromDate and promotion.toDate)";
		return jdbc.query(sql, params, new BeanPropertyRowMapper(Promotion.class));
	}
}
