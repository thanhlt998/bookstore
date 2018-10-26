package com.thanh.model;

import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {
	public static List<Date> createDateListByYearMonth(YearMonth yearMonth){
		List<Date> dateList = new ArrayList<>();
		for(int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
			dateList.add(Date.from(yearMonth.atDay(i).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}
		return dateList;
	}
}
