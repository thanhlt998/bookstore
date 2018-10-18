package com.thanh.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thanh.entity.User;
import com.thanh.enumeration.Authority;
import com.thanh.service.UserService;

@Controller
public class AdminController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/changeUserRole", method=RequestMethod.GET)
	public @ResponseBody String changeUserRole(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();
		int userId = Integer.parseInt(request.getParameter("userId"));
		Authority authority = Authority.valueOf(request.getParameter("newRole"));
		
		User user = userService.getUserByUserId(userId);
		user.setAuthority(authority);
		
		userService.updateUser(user);
		
		try {
			ajaxResponse = mapper.writeValueAsString(Boolean.TRUE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ajaxResponse;
	}
	
	@RequestMapping("/adminDashboard")
	public String viewAdminDashBoard() {
		return "admin";
	}
	
//	@RequestMapping(value="/viewUsers", method=RequestMethod.GET)
//	public @ResponseBody String viewUsers(HttpServletRequest request) {
//		String ajaxResponse = "";
//		ObjectMapper mapper = new ObjectMapper();
//		
//		int page = Integer.parseInt(request.getParameter("page"));
//		List<User> userList = userService.getUserListByPage(page);
//		
//		try {
//			ajaxResponse = mapper.writeValueAsString(userList);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return ajaxResponse;
//		
//	}
	
	@RequestMapping(value="/searchUsers", method=RequestMethod.GET)
	public @ResponseBody String searchUsers(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();
		
		int page = Integer.parseInt(request.getParameter("page"));
		String userId = request.getParameter("userId");
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String authority = request.getParameter("authority");
		List<User> userList = userService.searchUserListByPage(userId, username, name, authority, page);
		
		try {
			ajaxResponse = mapper.writeValueAsString(userList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ajaxResponse;
	}
}
