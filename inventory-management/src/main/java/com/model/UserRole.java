package com.model;
// Generated Apr 17, 2023, 5:39:06 AM by Hibernate Tools 6.1.7.Final

import java.sql.Timestamp;

/**
 * UserRole generated by hbm2java
 */
public class UserRole implements java.io.Serializable {

	private Integer id;
	private Role role;
	private Users users;
	private int activeFlag;
	private Timestamp createDate;
	private Timestamp updateDate;

	public UserRole() {
	}

	public UserRole(Role role, Users users, int activeFlag, Timestamp createDate, Timestamp updateDate) {
		this.role = role;
		this.users = users;
		this.activeFlag = activeFlag;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
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

}
