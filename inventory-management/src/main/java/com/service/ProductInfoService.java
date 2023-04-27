package com.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dao.BaseDAOImpl;
import com.dao.ProductInfoDAO;
import com.model.Paging;
import com.model.ProductInfo;
import com.util.ConfigLoader;
import com.util.Constant;

@Service
public class ProductInfoService {
	final static Logger log = Logger.getLogger(BaseDAOImpl.class);

	@Autowired
	ProductInfoDAO<ProductInfo> productInfoDAO;

	public void save(ProductInfo productInfo) throws Exception {
		log.info("Save ProductInfo:" + productInfo.toString());

		productInfo.setActiveFlag(1);
		productInfo.setCreateDate(new Date());
		productInfo.setUpdateDate(new Date());

		// tao file
		processUploadFIle(productInfo.getMultipartFile());

		// get ten file de luu vao db
		productInfo.setImgUrl(
				"/upload/" + System.currentTimeMillis() + "_" + productInfo.getMultipartFile().getOriginalFilename());
		productInfoDAO.save(productInfo);
	}

	public void update(ProductInfo productInfo) throws Exception {
		log.info("Update ProductInfo:" + productInfo.toString());

		// tao file
		processUploadFIle(productInfo.getMultipartFile());
		if (productInfo.getMultipartFile() != null) {
			productInfo.setImgUrl("/upload/" + System.currentTimeMillis() + "_"
					+ productInfo.getMultipartFile().getOriginalFilename());
		}

		productInfo.setUpdateDate(new Date());
		productInfoDAO.update(productInfo);
	}

	public void delete(ProductInfo productInfo) throws Exception {
		productInfo.setActiveFlag(0);
		log.info("Delete ProductInfo:" + productInfo.toString());

		productInfoDAO.update(productInfo);
	}

	public List<ProductInfo> findProductInfo(String property, Object object) {
		log.info("Find ProductInfo:" + property.toString());

		return productInfoDAO.findByProperty(property, object);
	}

	public List<ProductInfo> getAllProductInfo(ProductInfo productInfo, Paging paging) {
		log.info("Find all ProductInfo");

		// search
		StringBuilder queryStr = new StringBuilder();

		// luu nhung param truyen vao
		Map<String, Object> mapPamrams = new HashMap<>();
		if (productInfo != null) {
			if (productInfo.getId() != null && productInfo.getId() != 0) {
				queryStr.append(" AND model.id = :id");
				mapPamrams.put(Constant.ID, productInfo.getId());
			}
			if (productInfo.getCode() != null && !StringUtils.isEmpty(productInfo.getCode())) {
				queryStr.append(" AND model.code = :code");
				mapPamrams.put(Constant.CODE, productInfo.getCode());
			}
			if (productInfo.getName() != null && !StringUtils.isEmpty(productInfo.getName())) {
				queryStr.append(" AND model.name LIKE :name");
				mapPamrams.put(Constant.NAME, "%" + productInfo.getName() + "%");
			}
		}

		return productInfoDAO.findAll(queryStr.toString(), mapPamrams, paging);
	}

	public ProductInfo findByIdProductInfo(int id) {

		log.info("Find ID:" + id);
		return productInfoDAO.findById(ProductInfo.class, id);
	}

	public List<ProductInfo> findProductInfoByCode(String code) {
		log.info("Find ProductInfo By Code:" + code);

		return productInfoDAO.findProductInfoByCode(code);
	}

	private void processUploadFIle(MultipartFile multipartFile) throws IllegalStateException, IOException {
		if (multipartFile != null) {
			File dir = new File(ConfigLoader.getInstance().getValue("upload.location"));

			// check xem file tao chua, tranh filenotfound
			if (!dir.exists()) {
				dir.mkdir();
			}
			// lat ten file: lay time hien tai + file name
			String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();

			// tao file
			File file = new File(ConfigLoader.getInstance().getValue("upload.location"), fileName);

			// copy file tu client -> server
			multipartFile.transferTo(file);

		}
	}

}
