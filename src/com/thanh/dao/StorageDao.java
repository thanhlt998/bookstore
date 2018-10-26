package com.thanh.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thanh.entity.Storage;

@Repository
@Transactional
@Component("storageDao")
public class StorageDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getStorageIdList(){
		Criteria criteria = getSession().createCriteria(Storage.class);
		criteria.setProjection(Projections.property("storageId"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Storage> getStorageList(){
		Criteria criteria = getSession().createCriteria(Storage.class);
		return criteria.list();
	}
	
	public void updateStorage(Storage storage) {
		getSession().update(storage);
	}
	
	public void saveStorage(Storage storage) {
		getSession().save(storage);
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getStorageIdListByStockKeeperId(int stockKeeperId){
		Criteria criteria = getSession().createCriteria(Storage.class);
		criteria.add(Restrictions.eq("stockKeeperId", stockKeeperId));
		criteria.setProjection(Projections.property("storageId"));
		return criteria.list();
	}
}
