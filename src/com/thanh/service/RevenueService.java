package com.thanh.service;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanh.dao.OrderDao;
import com.thanh.model.Utils;

@Service("revenueService")
public class RevenueService {
	@Autowired
	private OrderDao orderDao;
	
	public List<Integer> getRevenuePerDayListByYearMonth(YearMonth yearMonth) {
		List<Date> dateList = Utils.createDateListByYearMonth(yearMonth);
		List<Integer> revenueList = new ArrayList<>();
		
		for(Date date: dateList) {
			revenueList.add(orderDao.getRevenuePerDayByDate(date));
		}
		
		return revenueList;
	}
	
	public List<Integer> getRevenuePerMonthListByYear(int year){
		YearMonth yearMonth = YearMonth.of(year, 1);
		List<Integer> revenueList = new ArrayList<>();
		for(int i = 1; i <= 12; i++) {
			revenueList.add(orderDao.getRevenuePerMonthByYearMonth(yearMonth.withMonth(i)));
		}
		
		return revenueList;
	}
}
