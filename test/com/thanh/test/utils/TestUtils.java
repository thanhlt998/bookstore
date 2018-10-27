package com.thanh.test.utils;

import java.time.YearMonth;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import com.thanh.model.Utils;

public class TestUtils {
	@Test
	public void testGetDateList() {
		List<Date> dateList = Utils.createDateListByYearMonth(YearMonth.parse("2018-10"));
		for(Date date: dateList) {
			System.out.println(date);
		}
	}
	
	@Test
	public void testPasswordEncoder() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("123456789"));
		System.out.println(passwordEncoder.encode("123456789").length());
	}
	
}
