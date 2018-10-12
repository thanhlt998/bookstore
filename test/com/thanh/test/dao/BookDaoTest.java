package com.thanh.test.dao;

import static org.junit.Assert.assertEquals;

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

import com.thanh.dao.BookDao;
import com.thanh.entity.Book;

import beforetest.GenerateDataTest;

@ActiveProfiles("dev")
@ContextConfiguration(value = { "classpath:com/thanh/config/dao-context.xml",
		"classpath:com/thanh/config/security-context.xml", "classpath:com/thanh/config/service-context.xml",
		"classpath:com/thanh/test/config/dataSource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BookDaoTest {
	@Autowired
	private BookDao bookDao;

	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		GenerateDataTest.generateData(jdbc);
	}
	
	@Test
	public void testGetBookByBookId() {
		Book book = GenerateDataTest.books.get(new Random().nextInt(10));
		Book result = bookDao.getBookByBookId(book.getBookId());
		
		assertEquals(book, result);
	}
	
	@Test
	public void testGetBookListByOffsetQuantity() {
		List<Book> books = bookDao.getBookListByOffsetQuantity(1, 5);
		assertEquals(5, books.size());
	}
}
