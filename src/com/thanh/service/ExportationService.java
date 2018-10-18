package com.thanh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanh.dao.ExportationDao;

@Service("exportationService")
public class ExportationService {
	@Autowired
	private ExportationDao exportationDao;
	
	public List<Integer> getBestSellerBookIdList(int limit){
		return exportationDao.getBookIdListBestSeller(limit);
	}
}
