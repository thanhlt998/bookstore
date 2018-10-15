package com.thanh.test.dao;

import static org.junit.Assert.assertEquals;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thanh.dao.ImportationDao;

import beforetest.GenerateDataTest;

@ActiveProfiles("dev")
@ContextConfiguration(value = { "classpath:com/thanh/config/dao-context.xml",
		"classpath:com/thanh/config/security-context.xml", "classpath:com/thanh/config/service-context.xml",
		"classpath:com/thanh/test/config/dataSource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ImportationDaoTest {
	@Autowired
	private ImportationDao importationDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		GenerateDataTest.generateData(jdbc);
	}
	
	@Test
	public void testCountNoBookImportedByBookId() {
		assertEquals(48, importationDao.countNoBookImportedByBookId(4));
		assertEquals(61, importationDao.countNoBookImportedByBookId(2));
	}
	
	@Test
	public void testCountImportedBookQuantityByBookIdStorageId() {
		assertEquals(0, importationDao.countImportedBookQuantityByBookIdStorageId(3, 1));
		assertEquals(38, importationDao.countImportedBookQuantityByBookIdStorageId(3, 6));
	}
}
