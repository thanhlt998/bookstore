package com.thanh.test.dao;

import static org.junit.Assert.assertEquals;

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

import com.thanh.dao.ManufacturerDao;
import com.thanh.entity.Manufacturer;

import beforetest.GenerateDataTest;

@ActiveProfiles("dev")
@ContextConfiguration(value = { "classpath:com/thanh/config/dao-context.xml",
		"classpath:com/thanh/config/security-context.xml", "classpath:com/thanh/config/service-context.xml",
		"classpath:com/thanh/test/config/dataSource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ManufacturerDaoTest {
	@Autowired
	private ManufacturerDao manufacturerDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		GenerateDataTest.generateData(jdbc);
	}
	
	@Test
	public void testGetManufacturerNameByManufacturerId() {
		Manufacturer manufacturer = GenerateDataTest.manufacturers.get(new Random().nextInt(10));
		String result = manufacturerDao.getManufacturerNameByManufacturerId(manufacturer.getManufacturerId());
		
		assertEquals(manufacturer.getManufacturerName(), result);
	}
	
	@Test
	public void testGetManufacturerByManufacturerId() {
		Manufacturer manufacturer = GenerateDataTest.manufacturers.get(new Random().nextInt(10));
		Manufacturer result = manufacturerDao.getManufacturerByManufacturerId(manufacturer.getManufacturerId());
		
		assertEquals(manufacturer, result);
	}
}
