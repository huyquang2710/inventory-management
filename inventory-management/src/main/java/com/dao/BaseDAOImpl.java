package com.dao;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = Exception.class) // rollback khi ex xay ra
@SuppressWarnings("unchecked")
public class BaseDAOImpl<E> implements BaseDAO<E> {

	final static Logger log = Logger.getLogger(BaseDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	// where: activeFlag = 1

	public List<E> findAll() {
		log.info("find all record from db");

		StringBuilder query = new StringBuilder();
		query.append(" FROM ").append(getGenericName()).append(" AS model WHERE model.activeFlag=1");

		log.info("Query find all => " + query.toString());

		return sessionFactory.getCurrentSession().createQuery(query.toString()).list();
	}

	// find id
	public E findById(Class<E> e, Serializable id) {
		log.info("find by id");
		return sessionFactory.getCurrentSession().get(e, id);
	}

	public List<E> findByProperty(String property, Object value) {
		log.info("find by property");

		StringBuilder queryString = new StringBuilder();
		queryString.append(" FROM").append(getGenericName()).append(" AS model WHERE model.activeFlag=1 and model.")
				.append(property).append("=?");

		log.info("Query findByProperty => " + queryString.toString());
		Query<E> query = sessionFactory.getCurrentSession().createQuery(queryString.toString());

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

	// lay ten table de truyen vao Hibernate
	public String getGenericName() {
		String s = getClass().getGenericSuperclass().toString();

		// kiem tra ten class co hop le ko?
		Pattern pattern = Pattern.compile("\\<(>*?)??>");
		Matcher m = pattern.matcher(s);
		String generic = null;
		if (m.find()) {
			generic = m.group();
		}
		return generic;
	}
}
