package com.dao;

import java.util.List;

import com.model.ProductInStock;

public interface ProductInStockDAO<E> extends BaseDAO<E> {
	public List<ProductInStock> findCategoryByCode(String code);
}
