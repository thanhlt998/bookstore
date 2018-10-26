package com.thanh.controller;

import java.io.IOException;
import java.time.YearMonth;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thanh.service.RevenueService;

@Controller
public class RevenueController {
	
	@Autowired
	private RevenueService revenueService;
	
	@RequestMapping(value="/getRevenuePerDay", method=RequestMethod.GET)
	public @ResponseBody String getRevenuePerDayByMonth(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();
		
		String month = request.getParameter("month");
		YearMonth yearMonth = YearMonth.parse(month);
		
		List<Integer> revenueList = revenueService.getRevenuePerDayListByYearMonth(yearMonth);
		try {
			ajaxResponse = mapper.writeValueAsString(revenueList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ajaxResponse;
	}
	
	@RequestMapping(value="/getRevenuePerMonth", method=RequestMethod.GET)
	public @ResponseBody String getRevenuePerMonthByYear(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();
		
		int year = Integer.parseInt(request.getParameter("year"));
		List<Integer> revenueList = revenueService.getRevenuePerMonthListByYear(year);
		
		try {
			ajaxResponse = mapper.writeValueAsString(revenueList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ajaxResponse;
	}
}
