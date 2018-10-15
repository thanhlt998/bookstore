package com.thanh.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thanh.entity.Exportation;
import com.thanh.entity.Order;
import com.thanh.entity.OrdersDetail;
import com.thanh.model.BookRemainInStorage;
import com.thanh.model.CartItem;

@Repository
@Transactional
@Component("orderCreationDao")
public class OrderCreationDao {
	@Autowired
	private ImportationDao importationDao;

	@Autowired
	private ExportationDao exportationDao;

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrdersDetailDao ordersDetailDao;

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//From here to get exportationList
	public int countRemainBookQuantityByBookIdStorageId(int bookId, int storageId) {
		int importedQuantity = importationDao.countImportedBookQuantityByBookIdStorageId(bookId, storageId);
		int exportedQuantity = exportationDao.countExportedBookQuantityByBookIdStorageId(bookId, storageId);
		return importedQuantity - exportedQuantity;
	}

	public List<BookRemainInStorage> getBookRemainInStorageListByBookIdStorageIdList(int bookId,
			List<Integer> storageIdList) {
		List<BookRemainInStorage> bookRemainInStorageList = new ArrayList<>();
		for (int storageId : storageIdList) {
			int remainQuantity = countRemainBookQuantityByBookIdStorageId(bookId, storageId);
			if (remainQuantity > 0) {
				bookRemainInStorageList.add(new BookRemainInStorage(bookId, storageId, remainQuantity));
			}
		}
		return bookRemainInStorageList;
	}

	public List<Exportation> getExportationListByQuantityBookRemainInStorageList(int quantity,
			List<BookRemainInStorage> bookRemainInStorageList) {
		Date today = Calendar.getInstance().getTime();
		List<Exportation> exportationList = new ArrayList<>();
		int quantityNeedToAdd = quantity;
		for (BookRemainInStorage bookRemainInStorage : bookRemainInStorageList) {
			if (bookRemainInStorage.getRemainQuantity() >= quantityNeedToAdd) {
				exportationList.add(new Exportation(bookRemainInStorage.getStorageId(), bookRemainInStorage.getBookId(),
						quantityNeedToAdd, today));
				break;
			} else if (bookRemainInStorage.getRemainQuantity() < quantityNeedToAdd) {
				exportationList.add(new Exportation(bookRemainInStorage.getStorageId(), bookRemainInStorage.getBookId(),
						bookRemainInStorage.getRemainQuantity(), today));
				quantityNeedToAdd -= bookRemainInStorage.getRemainQuantity();
			}
		}
		return exportationList;
	}

	public List<Exportation> getExportationListByBookIdQuantityStorageIdList(int bookId, int quantity,
			List<Integer> storageIdList) {
		List<BookRemainInStorage> bookRemainInStorageList = getBookRemainInStorageListByBookIdStorageIdList(bookId,
				storageIdList);
		List<Exportation> exportationList = getExportationListByQuantityBookRemainInStorageList(quantity,
				bookRemainInStorageList);
		return exportationList;
	}

	
	// get orderDetail list
	public List<OrdersDetail> getOrdersDetailListByOrderIdCartItemList(int orderId, List<CartItem> cartItemList) {
		List<OrdersDetail> ordersDetailList = new ArrayList<>();
		for (CartItem cartItem : cartItemList) {
			ordersDetailList.add(new OrdersDetail(orderId, cartItem.getBook().getBookId(), cartItem.getQuantity(),
					cartItem.getBook().getCurrentPrice()));
		}
		return ordersDetailList;
	}

	public boolean order(Order order, List<CartItem> cartItemList, List<Integer> storageIdList) {
		int orderId = orderDao.addOrder(order);
		
		List<OrdersDetail> ordersDetailList = getOrdersDetailListByOrderIdCartItemList(orderId, cartItemList);
		List<Exportation> exportationList = new ArrayList<>();
		
		for (CartItem cartItem : cartItemList) {
			exportationList.addAll(getExportationListByBookIdQuantityStorageIdList(cartItem.getBook().getBookId(),
					cartItem.getQuantity(), storageIdList));
		}
		
		System.out.println("ExportationList");
		for(Exportation exportation: exportationList) {
			System.out.println(exportation);
		}
		
		ordersDetailDao.addOrdersDetailList(ordersDetailList);
		exportationDao.addExportationList(exportationList);
		
		return getSession().getTransaction().wasCommitted();
	}
}
