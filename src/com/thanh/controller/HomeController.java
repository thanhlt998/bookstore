package com.thanh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
	public String showHome(Model model, HttpSession session) {
		// Book list
		List<BookListView> bookList = bookService.getBookListViewsByOffsetQuantity(0, 6);
		model.addAttribute("bookList", bookList);
		// Category list
		List<Category> categoryList = categoryService.getAllCategory();
		session.setAttribute("categoryList", categoryList);

		return "home";
	}
}
