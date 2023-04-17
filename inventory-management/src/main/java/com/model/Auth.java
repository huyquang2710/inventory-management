package com.model;
// Generated Apr 17, 2023, 5:39:06 AM by Hibernate Tools 6.1.7.Final

import java.sql.Timestamp;

/**
 * Auth generated by hbm2java
 */
public class Auth implements java.io.Serializable {

	private Integer id;
	private Role role;
	private Menu menu;
	private int permission;
	private int activeFlag;
	private Timestamp createDate;
	private Timestamp updateDate;

	public Auth() {
	}

	public Auth(Role role, Menu menu, int permission, int activeFlag, Timestamp createDate, Timestamp updateDate) {
		this.role = role;
		this.menu = menu;
		this.permission = permission;
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

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int getPermission() {
		return this.permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
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