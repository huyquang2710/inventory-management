package com.dao;

import java.util.List;

import com.model.Category;

public interface CategoryDAO<E> extends BaseDAO<E> {
	public List<Category> findCategoryByCode(String code);
}
