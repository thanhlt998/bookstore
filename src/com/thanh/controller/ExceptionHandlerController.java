package com.thanh.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(DataAccessException.class)
	public String handleDataAccessException() {
		return "dataAccessException";
	}
	
	@RequestMapping("/pageNotFound")
	public String handleNotFoundException() {
		return "notFoundException";
	}
	
	@RequestMapping("/badRequest")
	public String handleException() {
		return "badRequestException";
	}
	
	@RequestMapping("/internalServerError")
	public String internalServerError() {
		return "internalServerError";
	}
}
