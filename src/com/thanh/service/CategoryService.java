package com.thanh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanh.dao.CategoryDao;
import com.thanh.entity.Category;

@Service("categoryService")
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<Category> getAllCategory(){
		return categoryDao.getAllCategoriesOrderByCategoryName();
	}
	
	public String getCategoryNameByCategoryId(int categoryId) {
		return categoryDao.getCategoryNameByCategoryId(categoryId);
	}
	
	public Category getCategoryByCategoryId(int categoryId) {
		return categoryDao.getCategoryByCategoryId(categoryId);
	}
	
}
