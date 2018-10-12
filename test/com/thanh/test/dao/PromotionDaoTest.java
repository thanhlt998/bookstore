package com.thanh.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

import com.thanh.dao.PromotionDao;
import com.thanh.entity.Promotion;

import beforetest.GenerateDataTest;

@ActiveProfiles("dev")
@ContextConfiguration(value = { "classpath:com/thanh/config/dao-context.xml",
		"classpath:com/thanh/config/security-context.xml", "classpath:com/thanh/config/service-context.xml",
		"classpath:com/thanh/test/config/dataSource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class PromotionDaoTest {

	@Autowired
	private PromotionDao promotionDao;

	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		GenerateDataTest.generateData(jdbc);
	}

	@Test
	public void testGetPromotionByPromotionId() {
		Promotion promotion = GenerateDataTest.promotions.get(new Random().nextInt(10));
		Promotion result = promotionDao.getPromotionByPromotionId(promotion.getPromotionId());

		assertEquals(promotion, result);
	}

	@Test
	public void testGetAllCurrentPromotion() {
		Date today = Calendar.getInstance().getTime();
		List<Promotion> expected = new ArrayList<>();
		for (Promotion promotion : GenerateDataTest.promotions) {
			if ((promotion.getFromDate().before(today) && promotion.getToDate().after(today))
					|| promotion.getFromDate().equals(today) || promotion.getToDate().equals(today)) {
				expected.add(promotion);
			}
		}
		
		List<Promotion> actual = promotionDao.getAllCurrentPromotion();
		Collections.sort(expected, new PromotionComparator());
		Collections.sort(actual, new PromotionComparator());
		
		assertEquals(expected.toString(), actual.toString());
		
	}
	
	private class PromotionComparator implements Comparator<Promotion>{
		@Override
		public int compare(Promotion promotion1, Promotion promotion2) {
			int id1 = promotion1.getPromotionId();
			int id2 = promotion2.getPromotionId();
			if(id1 == id2) {
				return 0;
			}
			else if(id1 < id2) {
				return -1;
			}
			return 1;
		}
	}
}
