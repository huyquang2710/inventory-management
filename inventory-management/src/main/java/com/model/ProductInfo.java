package com.model;
// Generated Apr 17, 2023, 5:39:06 AM by Hibernate Tools 6.1.7.Final

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * ProductInfo generated by hbm2java
 */
public class ProductInfo implements java.io.Serializable {

	private Integer id;
	private Category category;
	private String code;
	private String name;
	private String description;
	private int activeFlag;
	private Timestamp createDate;
	private Timestamp updateDate;
	private Set productInStocks = new HashSet(0);
	private Set histories = new HashSet(0);
	private Set invoices = new HashSet(0);

	public ProductInfo() {
	}

	public ProductInfo(Category category, String code, String name, int activeFlag, Timestamp createDate,
			Timestamp updateDate) {
		this.category = category;
		this.code = code;
		this.name = name;
		this.activeFlag = activeFlag;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public ProductInfo(Category category, String code, String name, String description, int activeFlag,
			Timestamp createDate, Timestamp updateDate, Set productInStocks, Set histories, Set invoices) {
		this.category = category;
		this.code = code;
		this.name = name;
		this.description = description;
		this.activeFlag = activeFlag;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.productInStocks = productInStocks;
		this.histories = histories;
		this.invoices = invoices;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public Set getProductInStocks() {
		return this.productInStocks;
	}

	public void setProductInStocks(Set productInStocks) {
		this.productInStocks = productInStocks;
	}

	public Set getHistories() {
		return this.histories;
	}

	public void setHistories(Set histories) {
		this.histories = histories;
	}

	public Set getInvoices() {
		return this.invoices;
	}

	public void setInvoices(Set invoices) {
		this.invoices = invoices;
	}

}