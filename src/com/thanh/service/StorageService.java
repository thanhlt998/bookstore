package com.thanh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanh.dao.ExportationDao;
import com.thanh.dao.ImportationDao;

@Service("storageService")
public class StorageService {
	
	@Autowired
	private ImportationDao importationDao;
	
	@Autowired
	private ExportationDao exportationDao;
	
	public int checkNoBookCanAddByBookId(int bookId) {
		int noBookImported = importationDao.countNoBookImportedByBookId(bookId);
		int noBookExported = exportationDao.countNoBookExportedByBookId(bookId);
		return noBookImported - noBookExported;
	}
}
