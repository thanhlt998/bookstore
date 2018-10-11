package com.thanh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanh.dao.BookDao;
import com.thanh.dao.ImageDao;
import com.thanh.dao.PromotionDao;
import com.thanh.dao.PromotionEventDao;
import com.thanh.entity.Book;
import com.thanh.entity.Promotion;
import com.thanh.entity.PromotionEvent;
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
	
	public List<BookListView> getBookListViewsByOffsetQuantity(int offset, int quantity){
		List<Book> books = bookDao.getBookListByOffsetQuantity(offset, quantity);
		List<BookListView> views = new ArrayList<>();
		
		for(Book book: books) {
			int discount = 0;
			String imageUrl = imageDao.getAnImageUrlByBookId(book.getBookId());
			List<Integer> promotionIdList = promotionEventDao.getAllPromotionIdByBookId(book.getBookId());
			List<Promotion> currentPromotions = promotionDao.getAllCurrentPromotion(promotionIdList);
			
			for(Promotion promotion: currentPromotions) {
				discount += promotion.getDiscount();
			}
			
			views.add(new BookListView(book.getBookId(), book.getBookName(), imageUrl, book.getPrice(), discount));
		}
		
		return views;
	}
}
