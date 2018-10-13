package com.thanh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thanh.model.BookDetail;
import com.thanh.model.BookListView;
import com.thanh.service.BookService;
import com.thanh.service.CategoryService;

@Controller
public class ViewBookController {
	private static final int NO_BOOK_VIEW_BY_CATEGORY = 9;
	private static final int NO_BOOK_VIEW_BY_SEARCH_VALUE = 9;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/viewBookDetail")
	public String showViewBookDetail(Model model, HttpServletRequest request) {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		BookDetail bookDetail = bookService.getBookDetailByBookId(bookId);
		model.addAttribute("bookDetail", bookDetail);
		return "viewBookDetail";
	}
	
	@RequestMapping(value="/viewBookByCategory", method=RequestMethod.GET)
	public String showViewBookByCategory(Model model, HttpServletRequest request) {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		int pageNum = Integer.parseInt(request.getParameter("page"));
		int offset = (pageNum - 1) * NO_BOOK_VIEW_BY_CATEGORY;
		int noPage;
		
		
		if(pageNum != 1) {
			noPage = Integer.parseInt(request.getParameter("noPage"));
		}else {
			noPage = bookService.countNoBookByCategoryId(categoryId) / NO_BOOK_VIEW_BY_CATEGORY + 1;
		}
		
		List<BookListView> bookList = bookService.searchBookByCategory(categoryId, offset, NO_BOOK_VIEW_BY_CATEGORY);
		model.addAttribute("bookList", bookList);
		model.addAttribute("category", categoryService.getCategoryByCategoryId(categoryId));
		model.addAttribute("page", pageNum);
		model.addAttribute("noPage", noPage);
		return "viewBookByCategory";
	}
	
	@RequestMapping("/viewSearchResult")
	public String showViewSearchResult(Model model, HttpServletRequest request) {
		String searchValue = request.getParameter("searchValue");
		int pageNum = Integer.parseInt(request.getParameter("page"));
		int offset = (pageNum - 1) * NO_BOOK_VIEW_BY_SEARCH_VALUE;
		int noPage;
		
		
		if(pageNum != 1) {
			noPage = Integer.parseInt(request.getParameter("noPage"));
		}else {
			noPage = bookService.countNoBookBySearchValue(searchValue) / NO_BOOK_VIEW_BY_CATEGORY + 1;
		}
		
		List<BookListView> bookList = bookService.searchBookBySearchValue(searchValue, offset, NO_BOOK_VIEW_BY_CATEGORY);
		model.addAttribute("bookList", bookList);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("page", pageNum);
		model.addAttribute("noPage", noPage);
		return "viewSearchResult";
	}
}
