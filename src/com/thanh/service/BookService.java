package com.thanh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanh.dao.BookDao;
import com.thanh.dao.CategoryDao;
import com.thanh.dao.ImageDao;
import com.thanh.dao.ManufacturerDao;
import com.thanh.dao.PromotionDao;
import com.thanh.dao.PromotionEventDao;
import com.thanh.entity.Book;
import com.thanh.entity.Category;
import com.thanh.entity.Manufacturer;
import com.thanh.entity.Promotion;
import com.thanh.entity.PromotionEvent;
import com.thanh.model.BookDetail;
import com.thanh.model.BookListView;

@Service("bookService")
public class BookService {
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private ImageDao imageDao;
	
	@Autowired
	private PromotionDao promotionDao;
	
	@Autowired
	private PromotionEventDao promotionEventDao;
	
	@Autowired
	private ManufacturerDao manufacturerDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<BookListView> getBookListViewsByOffsetQuantity(int offset, int quantity){
		List<Book> books = bookDao.getBookListByOffsetQuantity(offset, quantity);
		for(Book book: books) {
			System.out.println(book);
		}
		List<BookListView> views = new ArrayList<>();
		
		for(Book book: books) {
			
			String imageUrl = imageDao.getAnImageUrlByBookId(book.getBookId());
			int discount = calculateTotalCurrentDiscountByBookId(book.getBookId());
			
			views.add(new BookListView(book.getBookId(), book.getBookName(), imageUrl, book.getPrice(), discount));
		}
		
		return views;
	}
	
	public BookDetail getBookDetailByBookId(int bookId) {
		Book book = bookDao.getBookByBookId(bookId);
		String category = categoryDao.getCategoryNameByCategoryId(book.getCategoryId());
		String manufacturer = manufacturerDao.getManufacturerNameByManufacturerId(book.getManufacturerId());
		List<String> imageUrls = imageDao.getImageUrlsByBookId(bookId);
		int discount = calculateTotalCurrentDiscountByBookId(bookId);
		
		return new BookDetail(book, category, manufacturer, imageUrls, discount);
	}
	
	public int calculateTotalCurrentDiscountByBookId(int bookId) {
		int discount = 0;
		List<Integer> promotionIdList = promotionEventDao.getAllPromotionIdByBookId(bookId);
		System.out.println(promotionIdList);
		
		List<Promotion> currentPromotions = promotionDao.getAllCurrentPromotion(promotionIdList);
		System.out.println(currentPromotions);
		
		for(Promotion promotion: currentPromotions) {
			discount += promotion.getDiscount();
		}
		return discount;
	}
}
