package com.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.Category;

@Repository
@Transactional(rollbackFor = Exception.class) // rollback khi ex xay ra
public class CategoryDAOImpl extends BaseDAOImpl<Category> implements CategoryDAO<Category> {

}
