package com.thanh.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thanh.model.BookListView;
import com.thanh.model.Cart;
import com.thanh.model.CartItem;
import com.thanh.service.BookService;
import com.thanh.service.StorageService;

@Controller
public class CartController {
	@Autowired
	private BookService bookService;

	@Autowired
	private StorageService storageService;

	@RequestMapping(value = "/addCart", method = RequestMethod.GET)
	public @ResponseBody String addCart(HttpServletRequest request, HttpSession session) {
		ObjectMapper mapper = new ObjectMapper();
		String ajaxResponse = "";

		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int quantity = 1;
		if (request.getParameter("quantity") != null) {
			quantity = Integer.parseInt(request.getParameter("quantity"));
		}
		BookListView book = bookService.getBookListViewByBookId(bookId);

		Cart cart = (Cart) session.getAttribute("cart");
		int noBookCanAdd = storageService.checkNoBookCanAddByBookId(bookId);
		int index = cart.isItemExisted(bookId);
		try {
			if (index != -1) {
				if (noBookCanAdd - cart.getCart().get(index).getQuantity() > quantity) {
					cart.increaseQuantityByBookId(index, bookId, quantity);
					ajaxResponse = mapper.writeValueAsString(cart);
				} else {
					ajaxResponse = mapper.writeValueAsString(Boolean.FALSE);
				}
			} else {
				if (noBookCanAdd > quantity) {
					CartItem newCartItem = new CartItem(book, quantity);
					cart.addCartItem(newCartItem);
					ajaxResponse = mapper.writeValueAsString(cart);
				} else {
					ajaxResponse = mapper.writeValueAsString(Boolean.FALSE);
				}
			}
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ajaxResponse;
	}
	
	@RequestMapping("/viewCart")
	public String showCart() {
		return "viewCart";
	}
	
	@RequestMapping("/removeCartItem")
	public @ResponseBody String removeCartItem(HttpSession session, HttpServletRequest request) {
		String ajaxResponse = "";
		Cart cart = (Cart) session.getAttribute("cart");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		cart.removeCartItemByBookId(bookId);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			ajaxResponse = mapper.writeValueAsString(cart);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ajaxResponse;
	}
}
