package com.thanh.test.service;

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

import com.thanh.entity.Book;
import com.thanh.model.BookDetail;
import com.thanh.model.BookListView;
import com.thanh.service.BookService;
import com.thanh.service.CategoryService;

import beforetest.GenerateDataTest;

@ActiveProfiles("dev")
@ContextConfiguration(value = { "classpath:com/thanh/config/dao-context.xml",
		"classpath:com/thanh/config/security-context.xml", "classpath:com/thanh/config/service-context.xml",
		"classpath:com/thanh/test/config/dataSource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		GenerateDataTest.generateData(jdbc);
	}
	
	@Test
	public void testGetBookListViewsByOffsetQuantity() {
		List<BookListView> bookListViews = bookService.getBookListViewsByOffsetQuantity(1, 5);
		assertEquals(5, bookListViews.size());
		for(BookListView bookListView: bookListViews) {
			System.out.println(bookListView);
		}
	}
	
	@Test
	public void testGetBookDetailByBookId() {
		Book book = GenerateDataTest.books.get(new Random().nextInt(10));
		BookDetail bookDetail = bookService.getBookDetailByBookId(book.getBookId());
		assertEquals(categoryService.getCategoryNameByCategoryId(book.getCategoryId()), bookDetail.getCategory());
		System.out.println(bookDetail);
	}
}
