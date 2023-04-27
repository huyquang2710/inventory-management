package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.ProductInfo;

@Repository
@Transactional(rollbackFor = Exception.class) // rollback khi ex xay ra
public class ProductInfoDAOImpl extends BaseDAOImpl<ProductInfo> implements ProductInfoDAO<ProductInfo> {

	@Override
	public List<ProductInfo> findProductInfoByCode(String code) {
		String hql = "SELECT o FROM ProductInfo o WHERE o.code = ?0 AND o.activeFlag=1";

		return super.findByProperty(ProductInfo.class, hql, code);
	}
}
