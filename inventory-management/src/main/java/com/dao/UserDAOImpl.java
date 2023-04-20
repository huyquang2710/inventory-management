package com.dao;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.Users;

@Repository
@Transactional(rollbackFor = Exception.class) // rollback khi ex xay ra
public class UserDAOImpl extends BaseDAOImpl<Users> implements UserDAO<Users> {

	@Autowired
	private SessionFactory factory;

	public void registerStudent(Users user) {
		Session session = factory.getCurrentSession();
		session.save(user);

	}

	public Users loginUser(Users user) {
		Session session = factory.getCurrentSession();
		try {
			Query<Users> query = session.createQuery("from Users where userName =:userName and password =:password",
					Users.class);
			query.setParameter("userName", user.getUserName());
			query.setParameter("password", user.getPassword());
			user = query.getSingleResult();
			return user;
		} catch (NoResultException e) {
			// TODO: handle exception
			return null;
		}
	}

	public Users findByUsernameAndPassword(String username, String password) {
		String hql = "SELECT o FROM Users o WHERE o.userName = ?0 AND o.password = ?1";
		return super.findOne(Users.class, hql, username, password);
	}

}
