package com.thanh.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thanh.entity.Order;
import com.thanh.entity.User;
import com.thanh.enumeration.Gender;
import com.thanh.service.OrderService;
import com.thanh.service.UserService;

@Controller
public class UserController {
	private static final int NO_ORDER_HISTORY_PER_TABLE = 10;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/viewProfile")
	public String showProfile(HttpServletRequest request, Principal principal) {
		String username = principal.getName();
		User user = userService.getUserByUsername(username);
		request.setAttribute("user", user);
		return "viewProfile";
	}
	
	@RequestMapping(value="/updateProfile", method=RequestMethod.GET)
	public @ResponseBody String updateProfile(HttpServletRequest request, Principal principal) {
		String username = principal.getName();
		User user = userService.getUserByUsername(username);
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		Date birthDate = null;
		try {
			birthDate = dateFormat.parse(request.getParameter("birthDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gender gender = Gender.valueOf(request.getParameter("gender"));
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		user.setName(name);
		user.setEmail(email);
		user.setBirthDate(birthDate);
		user.setGender(gender);
		user.setAddress(address);
		user.setPhone(phone);
		userService.updateUser(user);
		
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
	
	@RequestMapping(value="/viewOrderHistory", method=RequestMethod.GET)
	public @ResponseBody String viewOrderHistory(HttpSession session, Principal principal) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();
		
		String username = principal.getName();
		int userId = userService.getUserIdByUsername(username);
		
		List<Order> orderList = orderService.getOrderListByUserIdOffsetQuantity(userId, 0, NO_ORDER_HISTORY_PER_TABLE);
		
		try {
			ajaxResponse = mapper.writeValueAsString(orderList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ajaxResponse;
		
	}
	
	@RequestMapping(value="/changePassword", method=RequestMethod.GET)
	public @ResponseBody String changePassword(HttpServletRequest request, Principal principal) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();
		
		String username = principal.getName();
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		User user = userService.getUserByUsername(username);
		
		if(user.getPassword() == oldPassword) {
			user.setPassword(newPassword);
			userService.updateUser(user);
			try {
				ajaxResponse = mapper.writeValueAsString(Boolean.TRUE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				ajaxResponse = mapper.writeValueAsString(Boolean.FALSE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ajaxResponse;
	}
}
