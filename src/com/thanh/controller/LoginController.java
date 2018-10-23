package com.thanh.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thanh.entity.Category;
import com.thanh.entity.User;
import com.thanh.enumeration.Authority;
import com.thanh.enumeration.Gender;
import com.thanh.model.Cart;
import com.thanh.service.CategoryService;
import com.thanh.service.UserService;

@Controller
public class LoginController {

	private static ObjectMapper mapper = new ObjectMapper();
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/loginFailed")
	public String loginFailed() {
		return "loginFailedPage";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/checkAvailableUsername", method = RequestMethod.GET)
	public @ResponseBody String checkAvailableUsername(HttpServletRequest request) {
		String username = request.getParameter("username");
		String ajaxResponse = "";
		boolean isAvailable = userService.checkAvailableUsername(username);

		try {
			if (isAvailable) {
				ajaxResponse = mapper.writeValueAsString(Boolean.TRUE);
			} else {
				ajaxResponse = mapper.writeValueAsString(Boolean.FALSE);
			}
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(ajaxResponse);
		return ajaxResponse;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces="application/x-www-form-urlencoded;charset=UTF-8")
	public String registerAccount(HttpServletRequest request) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Date birthDate = null;
		try {
			birthDate = dateFormat.parse(request.getParameter("birthDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gender gender = Gender.valueOf(request.getParameter("gender"));
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");

		User user = new User(username, password, name, email, birthDate, gender, address, phone,
				Authority.ROLE_USER);
		System.out.println(username);
		System.out.println(password);
		System.out.println(name);
		System.out.println(email);
		System.out.println(birthDate);
		System.out.println(gender);
		System.out.println(address);
		System.out.println(phone);

		userService.createUser(user);

		return "redirect:/";
	}
}
