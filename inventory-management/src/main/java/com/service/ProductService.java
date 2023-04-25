package com.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDAOImpl;
import com.dao.CategoryDAO;
import com.model.Category;

@Service
public class ProductService {
	final static Logger log = Logger.getLogger(BaseDAOImpl.class);

	@Autowired
	private CategoryDAO<Category> categoryDAO;

	public void save(Category category) {
		log.info("Save Category:" + category.toString());

		category.setActiveFlag(1);
		category.setCreateDate(new Date());
		category.setUpdateDate(new Date());
		categoryDAO.save(category);
	}

	public void update(Category category) {
		log.info("Update Category:" + category.toString());

		category.setUpdateDate(new Date());
		categoryDAO.update(category);
	}

	public void delete(Category category) {
		category.setActiveFlag(0);
		log.info("Delete Category:" + category.toString());

		categoryDAO.update(category);
	}

	public List<Category> findCategory(String property, Object object) {
		log.info("Find Category:" + property.toString());

		return categoryDAO.findByProperty(property, object);
	}

	public List<Category> getAllCategory() {
		log.info("Find all Category");
		return categoryDAO.findAll();
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
