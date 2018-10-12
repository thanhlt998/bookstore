package com.thanh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thanh.entity.Category;
import com.thanh.model.BookListView;
import com.thanh.service.BookService;
import com.thanh.service.CategoryService;
import com.thanh.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/")
	public String showHome(Model model) {
		// Book list
		List<BookListView> bookList = bookService.getBookListViewsByOffsetQuantity(0, 6);
		if(bookList.size() > 3) {
			List<BookListView> bookList1 = new ArrayList<>(bookList.subList(0, 3));
			List<BookListView> bookList2 = new ArrayList<>(bookList.subList(3, bookList.size()));
			model.addAttribute("bookList1", bookList1);
			model.addAttribute("bookList2", bookList2);
		}
		else {
			model.addAttribute("bookList", bookList);
		}
		
		// Category list
		List<Category> categoryList = categoryService.getAllCategory();
		model.addAttribute("categoryList", categoryList);
		
		return "home";
	}
}
