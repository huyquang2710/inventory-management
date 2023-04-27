package com.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.model.Paging;

public interface BaseDAO<E> {
	public List<E> findAll(String queryStr, Map<String, Object> mapParams, Paging paging);

	public E findById(Class<E> e, Serializable id);

	public List<E> findByProperty(String property, Object value);

	public void save(E instance);

	public void update(E instance);

	public E findOne(Class<E> clazz, String hql, Object... params);

	public List<E> findByProperty(Class<E> clazz, String hql, Object... params);

}
