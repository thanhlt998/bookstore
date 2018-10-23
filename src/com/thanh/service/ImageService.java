package com.thanh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanh.dao.ImageDao;
import com.thanh.entity.Image;

@Service("imageService")
public class ImageService {
	@Autowired
	private ImageDao imageDao;
	
	public void addImage(Image image) {
		imageDao.saveImage(image);
	}
}
