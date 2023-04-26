package com.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.util.Constant;

@Repository
@Transactional(rollbackFor = Exception.class) // rollback khi ex xay ra
public class BaseDAOImpl<E> implements BaseDAO<E> {

	final static Logger log = Logger.getLogger(BaseDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	// where: activeFlag = 1
	public List<E> findAll(String queryStr, Map<String, Object> mapParams) {
		log.info("find all record from db");

		StringBuilder queryFindAll = new StringBuilder();
		queryFindAll.append(" FROM ").append(getGenericName()).append(" AS model WHERE model.activeFlag=1");
//		query.append(" FROM ").append("Category").append(" AS model WHERE model.activeFlag=1");

		// search
		if (queryStr != null && !queryStr.isEmpty()) {
			queryFindAll.append(queryStr);
		}

		Query<E> query = sessionFactory.getCurrentSession().createQuery(queryFindAll.toString());

		// set params vao cau query
		if (mapParams != null && !mapParams.isEmpty()) {
			for (String key : mapParams.keySet()) {
				query.setParameter(key, mapParams.get(key));
			}
		}

		log.info("Query find all => " + query.toString());

		return query.list();
	}

	// find id
	public E findById(Class<E> e, Serializable id) {
		log.info("find by id");
		return sessionFactory.getCurrentSession().get(e, id);
	}

	public List<E> findByProperty(String property, Object value) {

		log.info("find by property");

		StringBuilder queryString = new StringBuilder();

		queryString.append(" FROM ").append(getGenericName()).append(" AS model WHERE model.activeFlag=1 and model.")
				.append(property).append(" = ?0 ");

//		queryString.append(" FROM ").append(getGenericName()).append(" AS model WHERE model.activeFlag=1 and model.")
//				.append(property).append(" =:").append(property);

//		queryString.append(" FROM ").append("Users").append(" AS model WHERE model.activeFlag=1 and model.")
//		.append(property).append(" = '?0'");

		log.info("Query findByProperty => " + queryString.toString());
		Query<E> query = sessionFactory.getCurrentSession().createQuery(queryString.toString()).setParameter(property,
				property);

		return query.getResultList();
	}

	public void save(E instance) {
		log.info("save " + instance);
		sessionFactory.getCurrentSession().persist(instance);

	}

	public void update(E instance) {
		log.info("update " + instance);
		sessionFactory.getCurrentSession().merge(instance);

	}

	// find one
	// findOne(User.Class, hql, "user", "password");
	public E findOne(Class<E> clazz, String hql, Object... params) {
		TypedQuery<E> query = sessionFactory.getCurrentSession().createQuery(hql, clazz);

		// dem co bao nhieu param
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		// tranh truong hop query null se bi loi. nen dung List
		List<E> result = query.getResultList();
		if (result.isEmpty()) {
			return null;
		}
		// vi fine one nen get(0)
		return result.get(0);
	}

	// lay ten table de truyen vao Hibernate
	private String getGenericName() {
		String strBefore = getClass().getGenericSuperclass().toString();
		log.info("name class before: " + strBefore);
		// StringUtils.substringBetween(s, s)

		String strAfter = StringUtils.substringBetween(strBefore, Constant.LESS_THAN, Constant.MORE_THAN);

		log.info("name class after: " + strAfter);

//		// kiem tra ten class co hop le ko?
//		Pattern pattern = Pattern.compile("\\<(>*?)//>");
//		Matcher m = pattern.matcher(s);
//		String generic = null;
//		if (m.find()) {
//			generic = m.group(1);
//		}
//
//		log.info("name table: " + generic);
		return strAfter;

	}

	@Override
	public List<E> findByProperty(Class<E> clazz, String hql, Object... params) {
		TypedQuery<E> query = sessionFactory.getCurrentSession().createQuery(hql, clazz);

		// dem co bao nhieu param
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		// tranh truong hop query null se bi loi. nen dung List
		List<E> result = query.getResultList();
		if (result.isEmpty()) {
			return null;
		}
		// vi fine one nen get(0)
		return result;
	}
}
