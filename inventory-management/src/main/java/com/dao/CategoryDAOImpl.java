package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.Category;

@Repository
@Transactional(rollbackFor = Exception.class) // rollback khi ex xay ra
public class CategoryDAOImpl extends BaseDAOImpl<Category> implements CategoryDAO<Category> {

	@Override
	public List<Category> findCategoryByCode(String code) {

		String hql = "SELECT o FROM Category o WHERE o.code = ?0 AND o.activeFlag=1";

		return super.findByProperty(Category.class, hql, code);
	}

}
