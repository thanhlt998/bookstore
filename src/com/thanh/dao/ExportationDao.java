package com.thanh.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thanh.entity.Exportation;

@Repository
@Transactional
@Component("exportationDao")
public class ExportationDao {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Exportation> getAllExportationList() {
		Criteria criteria = getSession().createCriteria(Exportation.class);
		return criteria.list();
	}

	public int countNoBookExportedByBookId(int bookId) {
		Criteria criteria = getSession().createCriteria(Exportation.class);
		criteria.add(Restrictions.eq("bookId", bookId));
		criteria.setProjection(Projections.sum("quantity"));
		if (criteria.uniqueResult() == null)
			return 0;
		return Integer.parseInt(criteria.uniqueResult().toString());
	}

	public void addExportationList(List<Exportation> exportationList) {
		Session session = getSession();
		for (Exportation exportation : exportationList) {
			session.save(exportation);
		}
	}

	public boolean addExportation(Exportation exportation) {
		getSession().save(exportation);
		return getSession().getTransaction().wasRolledBack();
	}

	public int countExportedBookQuantityByBookIdStorageId(int bookId, int storageId) {
		Criteria criteria = getSession().createCriteria(Exportation.class);
		criteria.add(Restrictions.eq("storageId", storageId));
		criteria.add(Restrictions.eq("bookId", bookId));
		criteria.setProjection(Projections.sum("quantity"));
		if (criteria.uniqueResult() == null)
			return 0;
		return Integer.parseInt(criteria.uniqueResult().toString());
	}

	@SuppressWarnings({ "unchecked", "serial" })
	public List<Integer> getBookIdListBestSeller(int limit) {
		Criteria criteria = getSession().createCriteria(Exportation.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("bookId"));
		projectionList.add(Projections.sum("quantity").as("exportedQuantity"));
		projectionList.add(Projections.property("bookId"));
		criteria.setProjection(projectionList);
		criteria.addOrder(Order.desc("exportedQuantity"));
		criteria.setMaxResults(limit);
		criteria.setResultTransformer(new ResultTransformer() {

			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				return tuple[0];
			}

			@SuppressWarnings("rawtypes")
			@Override
			public List transformList(List collection) {
				return collection;
			}
		});
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Exportation> searchExportationByIdStorageIdBookIdExportDateOffsetQuantity(String exportationId,
			String storageId, String bookId, String exportDate, int offset, int quantity) {
		Criteria criteria = getSession().createCriteria(Exportation.class);
		if(!exportationId.equals("")) {
			criteria.add(Restrictions.idEq(Integer.parseInt(exportationId)));
		}
		if(!storageId.equals("")) {
			criteria.add(Restrictions.eq("storageId", Integer.parseInt(storageId)));
		}
		if(!bookId.equals("")) {
			criteria.add(Restrictions.eq("bookId", Integer.parseInt(bookId)));
		}
		if(!exportDate.equals("")) {
			try {
				criteria.add(Restrictions.eq("importDate", dateFormat.parse(exportDate)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		criteria.setFirstResult(offset);
		criteria.setMaxResults(quantity);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Exportation> searchExportationByIdStorageIdListBookIdExportDateOffsetQuantity(String exportationId,
			List<Integer> storageIdList, String bookId, String exportDate, int offset, int quantity) {
		Criteria criteria = getSession().createCriteria(Exportation.class);
		if(!exportationId.equals("")) {
			criteria.add(Restrictions.idEq(Integer.parseInt(exportationId)));
		}
		if(!storageIdList.isEmpty()) {
			criteria.add(Restrictions.in("storageId", storageIdList));
		}
		if(!bookId.equals("")) {
			criteria.add(Restrictions.eq("bookId", Integer.parseInt(bookId)));
		}
		if(!exportDate.equals("")) {
			try {
				criteria.add(Restrictions.eq("importDate", dateFormat.parse(exportDate)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		criteria.setFirstResult(offset);
		criteria.setMaxResults(quantity);
		return criteria.list();
	}
}
