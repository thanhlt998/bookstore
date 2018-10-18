package com.thanh.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

import com.thanh.dao.ExportationDao;
import com.thanh.entity.Exportation;

import beforetest.GenerateDataTest;

@ActiveProfiles("dev")
@ContextConfiguration(value = { "classpath:com/thanh/config/dao-context.xml",
		"classpath:com/thanh/config/security-context.xml", "classpath:com/thanh/config/service-context.xml",
		"classpath:com/thanh/test/config/dataSource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ExportationDaoTest {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private ExportationDao exportationDao;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		GenerateDataTest.generateData(jdbc);
	}
	
	@Test
	public void testCountNoBookExportedByBookId() {
		assertEquals(29, exportationDao.countNoBookExportedByBookId(3));
	}
	
	@Test
	public void testCountExportedBookQuantityByBookIdStorageId() {
		assertEquals(0, exportationDao.countExportedBookQuantityByBookIdStorageId(3, 1));
		assertEquals(0, exportationDao.countExportedBookQuantityByBookIdStorageId(3, 2));
		assertEquals(0, exportationDao.countExportedBookQuantityByBookIdStorageId(3, 6));
		assertEquals(29, exportationDao.countExportedBookQuantityByBookIdStorageId(3, 8));
	}
	
	@Test
	public void testAddExportation() {
		Exportation newExportation = new Exportation(6, 3, 5, new Date());
		boolean flag = exportationDao.addExportation(newExportation);
		System.out.println(flag);
		
		GenerateDataTest.exportations.add(newExportation);
		
		List<Exportation> result = exportationDao.getAllExportationList();
		
		Collections.sort(result, new ExportationComparator());
		Collections.sort(GenerateDataTest.exportations, new ExportationComparator());
		
		assertEquals(GenerateDataTest.exportations, result);
	}
	
	@Test
	public void testGetBookIdListBestSeller() {
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(7);
		expected.add(1);
		expected.add(2);
		expected.add(8);
		expected.add(5);
		expected.add(3);
		
		assertEquals(expected.toString(), new ArrayList<>(exportationDao.getBookIdListBestSeller(10)).toString());
	}
	
	private class ExportationComparator implements Comparator<Exportation>{
		@Override
		public int compare(Exportation o1, Exportation o2) {
			int id1 = o1.getExportationId();
			int id2 = o2.getExportationId();
			
			if(id1 == id2) {
				return 0;
			}
			else if(id1 > id2) {
				return 1;
			}
			return 0;
		}
	}
}
