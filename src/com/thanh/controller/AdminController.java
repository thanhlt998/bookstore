package com.thanh.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.thanh.entity.Book;
import com.thanh.entity.Category;
import com.thanh.entity.Image;
import com.thanh.entity.Manufacturer;
import com.thanh.entity.Order;
import com.thanh.entity.User;
import com.thanh.enumeration.Authority;
import com.thanh.enumeration.OrderStatus;
import com.thanh.model.FullOrder;
import com.thanh.service.BookService;
import com.thanh.service.CategoryService;
import com.thanh.service.ImageService;
import com.thanh.service.ManufacturerService;
import com.thanh.service.OrderService;
import com.thanh.service.UserService;

@Controller
public class AdminController {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ManufacturerService manufacturerService;

	@Autowired
	private BookService bookService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private ServletContext context;

	@RequestMapping(value = "/changeUserRole", method = RequestMethod.GET)
	public @ResponseBody String changeUserRole(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();
		int userId = Integer.parseInt(request.getParameter("userId"));
		Authority authority = Authority.valueOf(request.getParameter("newRole"));

		User user = userService.getUserByUserId(userId);
		user.setAuthority(authority);

		userService.updateUser(user);

		try {
			ajaxResponse = mapper.writeValueAsString(Boolean.TRUE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ajaxResponse;
	}

	@RequestMapping("/adminDashboard")
	public String viewAdminDashBoard() {
		return "admin";
	}

	@RequestMapping(value = "/searchUsers", method = RequestMethod.GET)
	public @ResponseBody String searchUsers(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();

		int page = Integer.parseInt(request.getParameter("page"));
		String userId = request.getParameter("userId");
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String authority = request.getParameter("authority");
		List<User> userList = userService.searchUserListByPage(userId, username, name, authority, page);

		try {
			ajaxResponse = mapper.writeValueAsString(userList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ajaxResponse;
	}

	@RequestMapping(value = "/searchOrders", method = RequestMethod.GET)
	public @ResponseBody String searchOrders(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();

		int page = Integer.parseInt(request.getParameter("page"));
		String orderId = request.getParameter("orderId");
		String userId = request.getParameter("userId");
		String orderStatus = request.getParameter("orderStatus");
		List<Order> orderList = orderService.searchOrderListByPage(orderId, userId, orderStatus, page);

		try {
			ajaxResponse = mapper.writeValueAsString(orderList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ajaxResponse;
	}

	@RequestMapping(value = "/changeOrder", method = RequestMethod.GET)
	public @ResponseBody String saveOrderChange(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();

		int orderId = Integer.parseInt(request.getParameter("orderId"));
		Date shipDate = null;
		try {
			shipDate = dateFormat.parse(request.getParameter("shipDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OrderStatus status = OrderStatus.valueOf(request.getParameter("orderStatus"));

		Order order = orderService.getOrderByOrderId(orderId);
		order.setShipDate(shipDate);
		order.setStatus(status);

		orderService.saveOrder(order);

		try {
			ajaxResponse = mapper.writeValueAsString(Boolean.TRUE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ajaxResponse;
	}

	@RequestMapping(value = "/adminViewOrderDetail", method = RequestMethod.GET)
	public @ResponseBody String viewOrderDetail(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();

		int orderId = Integer.parseInt(request.getParameter("orderId"));
		if (orderService.getOrderByOrderId(orderId) != null) {
			int userId = orderService.getOrderByOrderId(orderId).getUserId();
			FullOrder fullOrder = orderService.getFullOrderByOrderId(userId, orderId);

			try {
				ajaxResponse = mapper.writeValueAsString(fullOrder);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				ajaxResponse = mapper.writeValueAsString(Boolean.FALSE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}

		return ajaxResponse;
	}

	@RequestMapping(value = "/searchCategory", method = RequestMethod.GET)
	public @ResponseBody String searchCategory(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();

		String categoryId = request.getParameter("categoryId");
		String categoryName = request.getParameter("categoryName");

		List<Category> categoryList = categoryService.searchCategoryListByIdName(categoryId, categoryName);

		try {
			ajaxResponse = mapper.writeValueAsString(categoryList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ajaxResponse;
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public @ResponseBody String addCategory(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();

		String categoryName = request.getParameter("categoryName");
		Category category = new Category(categoryName);
		categoryService.addCategory(category);

		try {
			ajaxResponse = mapper.writeValueAsString(Boolean.TRUE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ajaxResponse;
	}

	@RequestMapping(value = "/searchManufacturer", method = RequestMethod.GET)
	public @ResponseBody String searchManufacturer(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();

		String manufacturerId = request.getParameter("manufacturerId");
		String manufacturerName = request.getParameter("manufacturerName");

		List<Manufacturer> manufacturerList = manufacturerService.searchManufacturerListByIdName(manufacturerId,
				manufacturerName);

		try {
			ajaxResponse = mapper.writeValueAsString(manufacturerList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ajaxResponse;
	}

	@RequestMapping(value = "/addManufacturer", method = RequestMethod.GET)
	public @ResponseBody String addManufacturer(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();

		String manufacturerName = request.getParameter("manufacturerName");
		Manufacturer manufacturer = new Manufacturer(manufacturerName);
		manufacturerService.addManufacturer(manufacturer);

		try {
			ajaxResponse = mapper.writeValueAsString(Boolean.TRUE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ajaxResponse;
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.GET)
	public @ResponseBody String addBook(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();

		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String bookName = request.getParameter("bookName");
		String bookDescription = request.getParameter("bookDescription");
		int manufacturerId = Integer.parseInt(request.getParameter("manufacturerId"));
		String author = request.getParameter("author");
		int price = Integer.parseInt(request.getParameter("price"));

		Book book = new Book(categoryId, bookName, bookDescription, manufacturerId, author, price);
		bookService.addBook(book);

		try {
			ajaxResponse = mapper.writeValueAsString(new Integer(book.getBookId()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ajaxResponse;
	}

	@RequestMapping(value = "/uploadImages", method = RequestMethod.POST)
	public @ResponseBody String uploadImages(@RequestParam("file0") MultipartFile file1,
			@RequestParam("file1") MultipartFile file2, @RequestParam("file2") MultipartFile file3,
			@RequestParam("bookId") int bookId) {
		List<MultipartFile> files = new ArrayList<>();
		files.add(file1);
		files.add(file2);
		files.add(file3);
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("size: " + files.size());
		System.out.println("bookId" + bookId);

		try {
			for (int i = 0; i < files.size(); i++) {
				if (!files.get(i).getOriginalFilename().isEmpty()) {
					String imageName = "book" + bookId + "_" + i + "."
							+ FilenameUtils.getExtension(files.get(i).getOriginalFilename());

					BufferedOutputStream outputStream = new BufferedOutputStream(
							new FileOutputStream(new File(context.getRealPath("/resources/images"), imageName)));
					imageService.addImage(new Image(bookId, imageName));
					outputStream.write(files.get(i).getBytes());
					outputStream.flush();
					outputStream.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			ajaxResponse = mapper.writeValueAsString(Boolean.TRUE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ajaxResponse;

	}
}
