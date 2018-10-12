package com.thanh.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thanh.model.BookDetail;
import com.thanh.service.BookService;

@Controller
public class ViewBookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/viewBookDetail")
	public String showViewBookDetail(Model model, HttpServletRequest request) {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		BookDetail bookDetail = bookService.getBookDetailByBookId(bookId);
		model.addAttribute("bookDetail", bookDetail);
		return "viewBookDetail";
	}
}
