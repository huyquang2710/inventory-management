package com.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDAOImpl;
import com.dao.CategoryDAO;
import com.model.Category;
import com.model.Paging;
import com.util.Constant;

@Service
public class ProductService {
	final static Logger log = Logger.getLogger(BaseDAOImpl.class);

	@Autowired
	private CategoryDAO<Category> categoryDAO;

	public void save(Category category) throws Exception {
		log.info("Save Category:" + category.toString());

		category.setActiveFlag(1);
		category.setCreateDate(new Date());
		category.setUpdateDate(new Date());
		categoryDAO.save(category);
	}

	public void update(Category category) throws Exception {
		log.info("Update Category:" + category.toString());

		category.setUpdateDate(new Date());
		categoryDAO.update(category);
	}

	public void delete(Category category) throws Exception {
		category.setActiveFlag(0);
		log.info("Delete Category:" + category.toString());

		categoryDAO.update(category);
	}

	public List<Category> findCategory(String property, Object object) {
		log.info("Find Category:" + property.toString());

		return categoryDAO.findByProperty(property, object);
	}

	public List<Category> getAllCategory(Category category, Paging paging) {
		log.info("Find all Category");

		// search
		StringBuilder queryStr = new StringBuilder();

		// luu nhung param truyen vao
		Map<String, Object> mapPamrams = new HashMap<>();
		if (category != null) {
			if (category.getId() != null && category.getId() != 0) {
				queryStr.append(" AND model.id = :id");
				mapPamrams.put(Constant.ID, category.getId());
			}
			if (category.getCode() != null && !StringUtils.isEmpty(category.getCode())) {
				queryStr.append(" AND model.code = :code");
				mapPamrams.put(Constant.CODE, category.getCode());
			}
			if (category.getName() != null && !StringUtils.isEmpty(category.getName())) {
				queryStr.append(" AND model.name LIKE :name");
				mapPamrams.put(Constant.NAME, "%" + category.getName() + "%");
			}
		}

		return categoryDAO.findAll(queryStr.toString(), mapPamrams, paging);
	}

	public Category findByIdCategory(int id) {

		log.info("Find ID:" + id);
		return categoryDAO.findById(Category.class, id);
	}

	public List<Category> findCategoryByCode(String code) {
		log.info("Find Category By Code:" + code);

		return categoryDAO.findCategoryByCode(code);
	}

}
