package com.thanh.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thanh.entity.Exportation;
import com.thanh.entity.Importation;
import com.thanh.entity.Storage;
import com.thanh.entity.User;
import com.thanh.enumeration.Authority;
import com.thanh.service.BookService;
import com.thanh.service.StorageService;
import com.thanh.service.UserService;

@Controller
public class StorageController {
	@Autowired
	private StorageService storageService;

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/viewStorage", method = RequestMethod.GET)
	public @ResponseBody String viewStorage() {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();

		List<Storage> storageList = storageService.getStorageList();

		try {
			ajaxResponse = mapper.writeValueAsString(storageList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ajaxResponse;
	}

	@RequestMapping(value = "/saveStorage", method = RequestMethod.GET)
	public @ResponseBody String saveStorage(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();

		int storageId = Integer.parseInt(request.getParameter("storageId"));
		String storageName = request.getParameter("storageName");
		String storageAddress = request.getParameter("storageAddress");
		int stockKeeperId = Integer.parseInt(request.getParameter("stockKeeperId"));

		Storage storage = new Storage(storageId, storageName, storageAddress, stockKeeperId);

		storageService.updateStorage(storage);

		try {
			ajaxResponse = mapper.writeValueAsString(Boolean.TRUE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ajaxResponse;
	}

	@RequestMapping(value = "/addStorage", method = RequestMethod.GET)
	public @ResponseBody String addStorage(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();

		String storageName = request.getParameter("storageName");
		String storageAddress = request.getParameter("storageAddress");
		int stockKeeperId = Integer.parseInt(request.getParameter("stockKeeperId"));

		Storage storage = new Storage(storageName, storageAddress, stockKeeperId);
		storageService.addStorage(storage);

		try {
			ajaxResponse = mapper.writeValueAsString(Boolean.TRUE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ajaxResponse;
	}

	@RequestMapping(value = "/getStorageIdList", method = RequestMethod.GET)
	public @ResponseBody String getStorageIdList(Principal principal) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();

		String username = principal.getName();
		User user = userService.getUserByUsername(username);

		List<Integer> storageIdList = null;

		if (user.getAuthority().equals(Authority.ROLE_ADMIN)) {
			storageIdList = storageService.getStorageIdList();
		} else {
			storageIdList = storageService.getStorageIdListByStockKeeperId(user.getUserId());
		}

		try {
			ajaxResponse = mapper.writeValueAsString(storageIdList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ajaxResponse;

	}

	@RequestMapping(value = "/checkBookIdExist", method = RequestMethod.GET)
	public @ResponseBody String checkBookIdExist(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();

		int bookId = Integer.parseInt(request.getParameter("bookId"));

		boolean isBookIdExist = bookService.checkBookIdExist(bookId);

		try {
			ajaxResponse = mapper.writeValueAsString(Boolean.valueOf(isBookIdExist));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ajaxResponse;
	}

	@RequestMapping(value = "/importBook", method = RequestMethod.GET)
	public @ResponseBody String importBook(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();

		int storageId = Integer.parseInt(request.getParameter("storageId"));
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int importPrice = Integer.parseInt(request.getParameter("importPrice"));

		Importation importation = new Importation(storageId, bookId, quantity, new Date(), importPrice);

		storageService.addImportation(importation);

		try {
			ajaxResponse = mapper.writeValueAsString(Boolean.TRUE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ajaxResponse;
	}

	@RequestMapping(value = "/viewImportation", method = RequestMethod.GET)
	public @ResponseBody String viewImportation(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();

		String importationId = request.getParameter("importationId");
		String storageId = request.getParameter("storageId");
		String bookId = request.getParameter("bookId");
		String importDate = request.getParameter("importDate");
		int page = Integer.parseInt(request.getParameter("page"));

		List<Importation> importationList = storageService
				.searchImportationByIdStorageIdBookIdImportDatePage(importationId, storageId, bookId, importDate, page);
		
		try {
			ajaxResponse = mapper.writeValueAsString(importationList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ajaxResponse;
	}
	
	@RequestMapping(value = "/viewExportation", method = RequestMethod.GET)
	public @ResponseBody String viewExportation(HttpServletRequest request) {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();

		String importationId = request.getParameter("exportationId");
		String storageId = request.getParameter("storageId");
		String bookId = request.getParameter("bookId");
		String exportDate = request.getParameter("exportDate");
		int page = Integer.parseInt(request.getParameter("page"));

		List<Exportation> exportationList = storageService
				.searchExportationByIdStorageIdBookIdExportDatePage(importationId, storageId, bookId, exportDate, page);
		
		try {
			ajaxResponse = mapper.writeValueAsString(exportationList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ajaxResponse;
	}
}
