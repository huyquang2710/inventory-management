package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.ProductInStock;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ProductInStockImpl extends BaseDAOImpl<ProductInStock> implements ProductInStockDAO<ProductInStock> {

	@Override
	public List<ProductInStock> findCategoryByCode(String code) {
		String hql = "SELECT o FROM ProductInStock o WHERE o.code = ?0 AND o.activeFlag=1";

		return super.findByProperty(ProductInStock.class, hql, code);
	}

}
