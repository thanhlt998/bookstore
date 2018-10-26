package com.thanh.test.utils;

import java.time.YearMonth;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.thanh.model.Utils;

public class TestUtils {
	@Test
	public void testGetDateList() {
		List<Date> dateList = Utils.createDateListByYearMonth(YearMonth.parse("2018-10"));
		for(Date date: dateList) {
			System.out.println(date);
		}
	}
	
}
