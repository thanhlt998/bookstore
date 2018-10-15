package com.thanh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanh.dao.ExportationDao;
import com.thanh.dao.ImportationDao;
import com.thanh.dao.StorageDao;

@Service("storageService")
public class StorageService {
	@Autowired
	private ImportationDao importationDao;
	
	@Autowired
	private ExportationDao exportationDao;
	@Autowired
	private StorageDao storageDao;
	
	public List<Integer> getStorageIdList(){
		return storageDao.getStorageIdList();
	}
	
	public int checkNoBookCanAddByBookId(int bookId) {
		int noBookImported = importationDao.countNoBookImportedByBookId(bookId);
		int noBookExported = exportationDao.countNoBookExportedByBookId(bookId);
		return noBookImported - noBookExported;
	}
}
