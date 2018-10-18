package com.thanh.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thanh.entity.Category;
import com.thanh.model.BookListView;
import com.thanh.model.Cart;
import com.thanh.service.BookService;
import com.thanh.service.CategoryService;
import com.thanh.service.ExportationService;
import com.thanh.service.UserService;

@Controller
public class HomeController {
	private static final int LIMIT_BOOK_SELLER_LIST = 10;

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ExportationService exportationService;

	@RequestMapping("/")
	public String showHome(Model model, HttpSession session) {
		// Book list
		List<BookListView> bookList = bookService.getBookListViewsByOffsetQuantity(0, 6);
		model.addAttribute("bookList", bookList);
		// Category list
		if(session.getAttribute("categoryList") == null) {
			List<Category> categoryList = categoryService.getAllCategory();
			session.setAttribute("categoryList", categoryList);
		}
		if(session.getAttribute("cart") == null) {
			session.setAttribute("cart", new Cart());
		}
		
		List<Integer> bestSellerBookIdList = exportationService.getBestSellerBookIdList(LIMIT_BOOK_SELLER_LIST);
		List<BookListView> bestSellerBookListview = bookService.getBookListViewListByBookIdList(bestSellerBookIdList);
		model.addAttribute("bestSellerBookList", bestSellerBookListview);

		return "home";
	}
}
