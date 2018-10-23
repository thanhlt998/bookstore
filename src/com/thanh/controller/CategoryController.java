package com.thanh.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thanh.entity.Category;
import com.thanh.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/loadCategory", method=RequestMethod.GET)
	public @ResponseBody String loadCategory() {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();
		
		List<Category> categoryList = categoryService.getAllCategory();
		
		try {
			ajaxResponse = mapper.writeValueAsString(categoryList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ajaxResponse;
	}
}
