package com.thanh.test.dao;

import static org.junit.Assert.assertEquals;

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

import com.thanh.dao.CategoryDao;
import com.thanh.entity.Category;

import beforetest.GenerateDataTest;

@ActiveProfiles("dev")
@ContextConfiguration(value = { "classpath:com/thanh/config/dao-context.xml",
		"classpath:com/thanh/config/security-context.xml", "classpath:com/thanh/config/service-context.xml",
		"classpath:com/thanh/test/config/dataSource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryDaoTest {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		GenerateDataTest.generateData(jdbc);
	}
	
	@Test
	public void testGetCategoryByCategoryId() {
		Category category = GenerateDataTest.categories.get(new Random().nextInt(10));
		System.out.println(category.getCategoryId() + " " + category.getCategoryName());
		Category result = categoryDao.getCategoryByCategoryId(category.getCategoryId());
		assertEquals(category, result);
		assertEquals(null, categoryDao.getCategoryByCategoryId(1321321));
	}
	
	@Test
	public void testGetAllCategoriesOrderByCategoryName() {
		Collections.sort(GenerateDataTest.categories, new CategoryComparator());
		List<Category> result = categoryDao.getAllCategoriesOrderByCategoryName();
		
		assertEquals(GenerateDataTest.categories.toString(), result.toString());
	}
	
	@Test
	public void testGetCategoryNameByCategoryId() {
		Category category = GenerateDataTest.categories.get(new Random().nextInt(10));
		String result = categoryDao.getCategoryNameByCategoryId(category.getCategoryId());
		assertEquals(category.getCategoryName(), result);
	}
	
	private class CategoryComparator implements Comparator<Category>{

		@Override
		public int compare(Category cat1, Category cat2) {
			return cat1.getCategoryName().compareTo(cat2.getCategoryName());
		}
		
	}
}
