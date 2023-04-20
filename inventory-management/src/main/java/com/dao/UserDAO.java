package com.dao;

import com.model.Users;

public interface UserDAO<E> extends BaseDAO<E> {

	public void registerStudent(Users user);

	public Users loginUser(Users user);

	public Users findByUsernameAndPassword(String username, String password);
}
