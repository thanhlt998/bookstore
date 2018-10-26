package com.thanh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StockKeeperController {
	
	@RequestMapping("/storageManagement")
	public String manageStorage() {
		return "storageManagement";
	}
}
