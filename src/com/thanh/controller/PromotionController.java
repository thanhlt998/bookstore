package com.thanh.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thanh.entity.Book;
import com.thanh.entity.Promotion;
import com.thanh.entity.PromotionEvent;
import com.thanh.service.PromotionService;

@Controller
public class PromotionController {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private PromotionService promotionService;

	@RequestMapping(value="/addPromotion", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String addPromotion(HttpServletRequest request) {
		String promotionDescription = request.getParameter("promotionDescription");
		Date fromDate = null;
		Date toDate = null;
		try {
			fromDate = dateFormat.parse(request.getParameter("fromDate"));
			toDate = dateFormat.parse(request.getParameter("toDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int discount = Integer.parseInt(request.getParameter("discount"));
		
		Promotion promotion = new Promotion(promotionDescription, fromDate, toDate, discount);
		
		promotionService.addPromotion(promotion);
		
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			ajaxResponse = mapper.writeValueAsString(Boolean.TRUE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ajaxResponse;
	}
	
	@RequestMapping(value="/viewPromotion", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String viewPromotion(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();
		
		String promotionId = request.getParameter("promotionId");
		String promotionDescription = request.getParameter("promotionDescription");
		int page = Integer.parseInt(request.getParameter("page"));
		
		List<Promotion> promotionList = promotionService.searchPromotionListByIdDescriptionPage(promotionId, promotionDescription, page);
		
		try {
			ajaxResponse = mapper.writeValueAsString(promotionList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ajaxResponse;
	}
	
	@RequestMapping(value="/checkPromotionAvailable", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String checkPromotionAvailable(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();
		
		int promotionId = Integer.parseInt(request.getParameter("promotionId"));
		
		boolean isPromotionAvailable = promotionService.checkPromotionAivalableByPromotionId(promotionId);
		
		try {
			ajaxResponse = mapper.writeValueAsString(Boolean.valueOf(isPromotionAvailable));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ajaxResponse;
	}
	
	@RequestMapping(value="/addPromotionEvent", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String addPromotionEvent(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();
		int promotionId = Integer.parseInt(request.getParameter("promotionId"));
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		
		PromotionEvent promotionEvent = new PromotionEvent(promotionId, bookId);
		promotionService.addPromotionEvent(promotionEvent);
		
		try {
			ajaxResponse = mapper.writeValueAsString(Boolean.TRUE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ajaxResponse;
	}
	
	@RequestMapping(value="/viewPromotionEvent", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String viewPromotionEvent(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();
		
		int promotionId = Integer.parseInt(request.getParameter("promotionId"));
		int page = Integer.parseInt(request.getParameter("page"));
		List<Book> bookList = promotionService.getBookListPromotionEventByPromotionIdPage(promotionId, page);
		
		try {
			ajaxResponse = mapper.writeValueAsString(bookList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ajaxResponse;
	}
}
