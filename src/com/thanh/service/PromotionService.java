package com.thanh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanh.dao.BookDao;
import com.thanh.dao.PromotionDao;
import com.thanh.dao.PromotionEventDao;
import com.thanh.entity.Book;
import com.thanh.entity.Promotion;
import com.thanh.entity.PromotionEvent;

@Service("promotionService")
public class PromotionService {
	private static final int NO_PROMOTION_PER_PAGE = 10;

	@Autowired
	private PromotionDao promotionDao;
	
	@Autowired
	private PromotionEventDao promotionEventDao;
	
	@Autowired
	private BookDao bookDao;

	public void addPromotion(Promotion promotion) {
		promotionDao.savePromotion(promotion);
	}

	public List<Promotion> searchPromotionListByIdDescriptionPage(String promotionId, String promotionDescription,
			int page) {
		return promotionDao.searchPromotionListByIdDescriptionOffsetQuantity(promotionId, promotionDescription,
				page * NO_PROMOTION_PER_PAGE, NO_PROMOTION_PER_PAGE);
	}
	
	public boolean checkPromotionAivalableByPromotionId(int promotionId) {
		return promotionDao.isPromotionAvailable(promotionId);
	}
	
	public void addPromotionEvent(PromotionEvent promotionEvent) {
		promotionEventDao.savePromotionEvent(promotionEvent);
	}
	
	public List<Book> getBookListPromotionEventByPromotionIdPage(int promotionId, int page) {
		List<Integer> bookIdList = promotionEventDao.getBookIdListByPromotionIdOffsetQuantity(promotionId, page*NO_PROMOTION_PER_PAGE, NO_PROMOTION_PER_PAGE);
		List<Book> bookList = bookDao.getBookListByBookIdList(bookIdList);
		return bookList;
	}
}
