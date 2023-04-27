package com.dao;

import java.util.List;

import com.model.ProductInfo;

public interface ProductInfoDAO<E> extends BaseDAO<E> {
	
	List<ProductInfo> findProductInfoByCode(String code);
}
