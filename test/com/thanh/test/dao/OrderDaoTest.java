package com.thanh.test.dao;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
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

import com.thanh.dao.OrderDao;
import com.thanh.service.RevenueService;

import beforetest.GenerateDataTest;

@ActiveProfiles("dev")
@ContextConfiguration(value = { "classpath:com/thanh/config/dao-context.xml",
		"classpath:com/thanh/config/security-context.xml", "classpath:com/thanh/config/service-context.xml",
		"classpath:com/thanh/test/config/dataSource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderDaoTest {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private RevenueService revenueService;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		GenerateDataTest.generateData(jdbc);
	}
	
	/*@Test
	public void testGetRevenuePerDayListByMonth() {
		List<Integer> revenueList = orderDao.getRevenuePerDayListByMonth(YearMonth.parse("2018-06"));
		System.out.println(revenueList);
	}*/
	
	@Test
	public void getRevenuePerDayByDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse("2017-11-18");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(141857, orderDao.getRevenuePerDayByDate(date));
	}
	
	@Test
	public void testGetRevenuePerDayByMonth() {
		List<Integer> revenueList = revenueService.getRevenuePerDayListByYearMonth(YearMonth.parse("2018-06"));
		System.out.println(revenueList);
	}
}
