package com.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDAO;
import com.model.Users;

@Service
public class UserService {
	final static Logger log = Logger.getLogger(UserService.class);

	@Autowired
	private UserDAO<Users> userDAO;

	// find user
	public List<Users> findByProperty(String property, Object value) {
		log.info("Find user by property: " + property + (" start"));

		return userDAO.findByProperty(property, value);
	}
	
//	public Users loginUser(Users users) {
//		
//		log.info("Find user by property: " + users.getName() + (" start"));
//		
//		return userDAO.loginUser(users);
//	}
	
	public Users login(String username, String password) {
		return userDAO.findByUsernameAndPassword(username, password);
	}
}
