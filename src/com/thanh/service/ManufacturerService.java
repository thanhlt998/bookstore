package com.thanh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanh.dao.ManufacturerDao;
import com.thanh.entity.Manufacturer;

@Service("manufacturerService")
public class ManufacturerService {
	@Autowired
	private ManufacturerDao manufacturerDao;
	
	public List<Manufacturer> searchManufacturerListByIdName(String manufacturerId, String manufacturerName){
		return manufacturerDao.searchManufacturerListByIdName(manufacturerId, manufacturerName);
	}
	
	public void addManufacturer(Manufacturer manufacturer) {
		manufacturerDao.addManufacturer(manufacturer);
	}
	
	public List<Manufacturer> getAllMenufacturerList() {
		return manufacturerDao.getAllManufacturerList();
	}
}
