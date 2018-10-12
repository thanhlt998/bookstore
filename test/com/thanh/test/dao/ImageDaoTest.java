package com.thanh.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
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

import com.thanh.dao.ImageDao;
import com.thanh.entity.Book;
import com.thanh.entity.Image;

import beforetest.GenerateDataTest;

@ActiveProfiles("dev")
@ContextConfiguration(value = { "classpath:com/thanh/config/dao-context.xml",
		"classpath:com/thanh/config/security-context.xml", "classpath:com/thanh/config/service-context.xml",
		"classpath:com/thanh/test/config/dataSource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ImageDaoTest {
	@Autowired
	private ImageDao imageDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		GenerateDataTest.generateData(jdbc);
	}
	
	@Test
	public void testGetImageUrlsByBookId() {
		Book book = GenerateDataTest.books.get(new Random().nextInt(10));
		List<String> actual = new ArrayList<>();
		for(Image image: GenerateDataTest.images) {
			if(image.getBookId() == book.getBookId()) {
				actual.add(image.getImageUrl());
			}
		}
		List<String> result = imageDao.getImageUrlsByBookId(book.getBookId());
		Collections.sort(actual);
		Collections.sort(result);
		
		assertEquals(actual.toString(), result.toString());
	}
	
}
