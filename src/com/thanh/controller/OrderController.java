package com.thanh.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thanh.entity.Order;
import com.thanh.enumeration.OrderStatus;
import com.thanh.enumeration.PaymentMethod;
import com.thanh.model.Cart;
import com.thanh.model.FullOrder;
import com.thanh.service.OrderService;
import com.thanh.service.StorageService;
import com.thanh.service.UserService;

@Controller
public class OrderController {
	@Autowired
	private UserService userService;

	@Autowired
	private StorageService storageService;

	@Autowired
	private OrderService orderService;

	@RequestMapping(value="/createOrder", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String createOrder(HttpSession session, HttpServletRequest request, Principal principal) {
		String ajaxResponse = "";

		// Get user info
		String username = principal.getName();
		int userId = userService.getUserIdByUsername(username);

		// Get cart
		Cart cart = (Cart) session.getAttribute("cart");

		// Get ship info
		Date orderDate = new Date();
		Date shipDate = getShipDate(orderDate);
		String shipAddress = request.getParameter("shipAddress");
		PaymentMethod paymentMethod = PaymentMethod.valueOf(request.getParameter("paymentMethod"));

		Order order = new Order(userId, orderDate, shipDate, shipAddress, cart.getTotalPrice(), paymentMethod,
				OrderStatus.PREPARING);
		List<Integer> storageIdList = storageService.getStorageIdList();

		boolean isOrderCreated = orderService.createOrder(order, cart.getCart(), storageIdList);
		ObjectMapper mapper = new ObjectMapper();
		FullOrder fullOrder = orderService.getFullOrderByOrderId(userId, order.getOrderId());
		try {
			ajaxResponse = mapper.writeValueAsString(fullOrder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("cart", new Cart());
		return ajaxResponse;
	}

	public Date getShipDate(Date orderDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(orderDate);
		calendar.add(Calendar.DATE, 2);
		return calendar.getTime();
	}
}
