package com.thanh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanh.dao.BookDao;
import com.thanh.dao.OrderCreationDao;
import com.thanh.dao.OrderDao;
import com.thanh.dao.OrdersDetailDao;
import com.thanh.dao.UserDao;
import com.thanh.entity.Book;
import com.thanh.entity.Order;
import com.thanh.entity.OrdersDetail;
import com.thanh.entity.User;
import com.thanh.model.CartItem;
import com.thanh.model.FullOrder;
import com.thanh.model.OrdersDetailItem;

@Service("orderService")
public class OrderService {
	private static final int NO_ORDER_HISTORY_PER_TABLE = 10;
	
	@Autowired
	private OrderCreationDao orderCreationDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrdersDetailDao ordersDetailDao;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private UserDao userDao;
	
	public boolean createOrder(Order order, List<CartItem> cartItemList, List<Integer> storageIdList) {
		return orderCreationDao.order(order, cartItemList, storageIdList);
	}
	
	public Order getOrderByOrderId(int orderId) {
		return orderDao.getOrderByOrderId(orderId);
	}
	
	public void saveOrder(Order order) {
		orderDao.addOrder(order);
	}
	
	public List<Order> getOrderListByUserIdPageNum(int userId, int page){
		return orderDao.getOrderListByUserIdOffsetQuantity(userId, page * NO_ORDER_HISTORY_PER_TABLE, NO_ORDER_HISTORY_PER_TABLE);
	}
	
	public boolean checkExistOrderByUserIdOrderId(int userId, int orderId) {
		return orderDao.checkExistOrderByUserIdOrderId(userId, orderId);
	}
	
	public List<OrdersDetailItem> getOrdersDetailItemListByOrdersDetailList(List<OrdersDetail> ordersDetailList){
		List<OrdersDetailItem> ordersDetailItemList = new ArrayList<>();
		for(OrdersDetail ordersDetail: ordersDetailList) {
			Book book = bookDao.getBookByBookId(ordersDetail.getBookId());
			ordersDetailItemList.add(new OrdersDetailItem(book.getBookName(), ordersDetail));
		}
		return ordersDetailItemList;
	}
	
	public FullOrder getFullOrderByOrderId(int userId, int orderId) {
		User user = userDao.getUserByUserId(userId);
		Order order = orderDao.getOrderByOrderId(orderId);
		List<OrdersDetail> ordersDetailList = ordersDetailDao.getOrdersDetailListByOrderId(orderId);
		List<OrdersDetailItem> ordersDetailItemList = getOrdersDetailItemListByOrdersDetailList(ordersDetailList);
		return new FullOrder(user, order, ordersDetailItemList);
	}
	
	public List<Order> searchOrderListByPage(String orderId, String userId, String orderStatus, int page){
		return orderDao.searchOrderList(orderId, userId, orderStatus, NO_ORDER_HISTORY_PER_TABLE * page, NO_ORDER_HISTORY_PER_TABLE);
	}
}
