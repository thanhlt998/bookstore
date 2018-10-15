package com.thanh.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thanh.dao.OrderCreationDao;
import com.thanh.dao.StorageDao;
import com.thanh.entity.Exportation;
import com.thanh.model.BookRemainInStorage;

import beforetest.GenerateDataTest;

@ActiveProfiles("dev")
@ContextConfiguration(value = { "classpath:com/thanh/config/dao-context.xml",
		"classpath:com/thanh/config/security-context.xml", "classpath:com/thanh/config/service-context.xml",
		"classpath:com/thanh/test/config/dataSource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderCreationDaoTest {
	@Autowired
	private DataSource dataSource;

	@Autowired
	private StorageDao storageDao;

	@Autowired
	private OrderCreationDao orderCreationDao;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		GenerateDataTest.generateData(jdbc);
	}

	@Test
	public void testCountRemainBookQuantityByBookIdStorageId() {
		assertEquals(38, orderCreationDao.countRemainBookQuantityByBookIdStorageId(3, 6));
	}

	@Test
	public void testGetBookRemainInStorageListByBookIdStorageIdList() {
		List<BookRemainInStorage> bookRemainInStorageList = orderCreationDao
				.getBookRemainInStorageListByBookIdStorageIdList(3, storageDao.getStorageIdList());
		for(BookRemainInStorage bookRemainInStorage: bookRemainInStorageList) {
			System.out.println(bookRemainInStorage);
		}
	}
	
	@Test
	public void testGetExportationListByQuantityBookRemainInStorageList() {
		List<BookRemainInStorage> bookRemainInStorageList = orderCreationDao
				.getBookRemainInStorageListByBookIdStorageIdList(3, storageDao.getStorageIdList());
		List<Exportation> exportationList = orderCreationDao.getExportationListByQuantityBookRemainInStorageList(5, bookRemainInStorageList);
		
		System.out.println("GetExportationListByQuantityBookRemainInStorageList");
		for(Exportation exportation: exportationList) {
			System.out.println(exportation);
		}
	}
	
	@Test
	public void testGetExportationListByBookIdQuantityStorageIdList() {
		List<Exportation> exportationList = orderCreationDao.getExportationListByBookIdQuantityStorageIdList(3, 5, storageDao.getStorageIdList());
		
		System.out.println("GetExportationListByBookIdQuantityStorageIdList");
		for(Exportation exportation: exportationList) {
			System.out.println(exportation);
		}
	}
}