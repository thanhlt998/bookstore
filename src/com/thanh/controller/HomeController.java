package com.thanh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thanh.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String showHome() {
		return "home";
	}
}
