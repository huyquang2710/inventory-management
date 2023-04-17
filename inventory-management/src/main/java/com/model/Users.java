package com.model;
// Generated Apr 17, 2023, 5:39:06 AM by Hibernate Tools 6.1.7.Final

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Users generated by hbm2java
 */
public class Users implements java.io.Serializable {

	private Integer id;
	private String userName;
	private String password;
	private String email;
	private String name;
	private int activeFlag;
	private Timestamp createDate;
	private Timestamp updateDate;
	private Set userRoles = new HashSet(0);

	public Users() {
	}

	public Users(String userName, String password, String email, String name, int activeFlag, Timestamp createDate,
			Timestamp updateDate) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.name = name;
		this.activeFlag = activeFlag;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public Users(String userName, String password, String email, String name, int activeFlag, Timestamp createDate,
			Timestamp updateDate, Set userRoles) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.name = name;
		this.activeFlag = activeFlag;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.userRoles = userRoles;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Set getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set userRoles) {
		this.userRoles = userRoles;
	}

}