package com.thanh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanh.dao.ExportationDao;
import com.thanh.dao.ImportationDao;
import com.thanh.dao.StorageDao;
import com.thanh.entity.Exportation;
import com.thanh.entity.Importation;
import com.thanh.entity.Storage;

@Service("storageService")
public class StorageService {
	private static final int NO_IMPORTATION_PER_PAGE = 10;
	private static final int NO_EXPORTATION_PER_PAGE = 10;

	@Autowired
	private ImportationDao importationDao;

	@Autowired
	private ExportationDao exportationDao;
	@Autowired
	private StorageDao storageDao;

	public List<Integer> getStorageIdList() {
		return storageDao.getStorageIdList();
	}

	public int checkNoBookCanAddByBookId(int bookId) {
		int noBookImported = importationDao.countNoBookImportedByBookId(bookId);
		int noBookExported = exportationDao.countNoBookExportedByBookId(bookId);
		return noBookImported - noBookExported;
	}

	public List<Storage> getStorageList() {
		return storageDao.getStorageList();
	}

	public void updateStorage(Storage storage) {
		storageDao.updateStorage(storage);
	}

	public void addStorage(Storage storage) {
		storageDao.saveStorage(storage);
	}

	public List<Integer> getStorageIdListByStockKeeperId(int stockKeeperId) {
		return storageDao.getStorageIdListByStockKeeperId(stockKeeperId);
	}

	public void addImportation(Importation importation) {
		importationDao.addImportation(importation);
	}

	public List<Importation> searchImportationByIdStorageIdBookIdImportDatePage(String importationId, String storageId,
			String bookId, String importDate, int page) {
		return importationDao.searchImportationByIdStorageIdBookIdImportDateOffsetQuantity(importationId, storageId,
				bookId, importDate, page * NO_IMPORTATION_PER_PAGE, NO_IMPORTATION_PER_PAGE);
	}
	
	public List<Exportation> searchExportationByIdStorageIdBookIdExportDatePage(String importationId, String storageId,
			String bookId, String exportDate, int page) {
		return exportationDao.searchExportationByIdStorageIdBookIdExportDateOffsetQuantity(importationId, storageId,
				bookId, exportDate, page * NO_EXPORTATION_PER_PAGE, NO_EXPORTATION_PER_PAGE);
	}
}
