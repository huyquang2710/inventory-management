package com.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dao.ProductInStockDAO;
import com.model.Invoice;
import com.model.Paging;
import com.model.ProductInStock;
import com.model.ProductInfo;

@Service
public class ProductInStockService {
	private static final Logger log = Logger.getLogger(ProductInStockService.class);

	@Autowired
	private ProductInStockDAO<ProductInStock> productInStockDAO;

	public List<ProductInStock> getAll(ProductInStock productInStock, Paging paging) {
		log.info("show all product in stock");
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<>();

		if (productInStock != null && productInStock.getProductInfo() != null) {

			if (productInStock != null && productInStock.getProductInfo() != null) {
				if (!StringUtils.isEmpty(productInStock.getProductInfo().getCategory().getName())) {
					queryStr.append(" and model.productInfo.category.name like :cateName");
					mapParams.put("cateName", "%" + productInStock.getProductInfo().getCategory().getName() + "%");
				}
				if (productInStock.getProductInfo().getCode() != null
						&& !StringUtils.isEmpty(productInStock.getProductInfo().getCode())) {
					queryStr.append(" and model.productInfo.code=:code");
					mapParams.put("code", productInStock.getProductInfo().getCode());
				}
				if (productInStock.getProductInfo().getName() != null
						&& !StringUtils.isEmpty(productInStock.getProductInfo().getName())) {
					queryStr.append(" and model.productInfo.name like :name");
					mapParams.put("name", "%" + productInStock.getProductInfo().getName() + "%");
				}
			}
		}
		return productInStockDAO.findAll(queryStr.toString(), mapParams, paging);
	}

	public void saveOrUpdate(Invoice invoice) throws Exception {
		log.info("Product in stock");
		if (invoice.getProductInfo() != null) {
			String code = invoice.getProductInfo().getCode();
			ProductInStock productInStock = productInStockDAO.findCategoryByCode(code).get(0);

			// neu productInStock != null thi update
			if (productInStock != null) {
				log.info("Update Qty = " + invoice.getQty() + " and Price = " + invoice.getPrice());
				productInStock.setQty(productInStock.getQty() + invoice.getQty());
				// type =1 receipt , type =2 issues
				if (invoice.getType() == 1) {
					productInStock.setPrice(invoice.getPrice());
				}
				productInStock.setUpdateDate(new Date());

				// update
				productInStockDAO.update(productInStock);
			} else {
				log.info("insert to stock qty=" + invoice.getQty() + " and price=" + invoice.getPrice());
				productInStock = new ProductInStock();
				ProductInfo productInfo = new ProductInfo();
				productInfo.setId(invoice.getProductInfo().getId());
				productInStock.setProductInfo(productInfo);
				productInStock.setActiveFlag(1);
				productInStock.setCreateDate(new Date());
				productInStock.setUpdateDate(new Date());
				productInStock.setQty(invoice.getQty());
				productInStock.setPrice(invoice.getPrice());

				// ad
				productInStockDAO.save(productInStock);
			}

		}
	}
}
