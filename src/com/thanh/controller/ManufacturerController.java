package com.thanh.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thanh.entity.Manufacturer;
import com.thanh.service.ManufacturerService;

@Controller
public class ManufacturerController {
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	@RequestMapping(value="/loadManufacturer", method=RequestMethod.GET)
	public @ResponseBody String loadManufacturer() {
		String ajaxResponse = "";
		ObjectMapper mapper = new ObjectMapper();
		
		List<Manufacturer> manufacturerList = manufacturerService.getAllMenufacturerList();
		
		try {
			ajaxResponse = mapper.writeValueAsString(manufacturerList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ajaxResponse;
	}
}
