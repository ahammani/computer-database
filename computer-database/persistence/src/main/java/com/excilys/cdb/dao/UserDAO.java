package com.excilys.cdb.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.User;

@Repository
public class UserDAO implements IUserDAO {
	@Autowired
	private SessionFactory sf;

	@Override
	public User findByUserName(String username) {
		Session session = sf.getCurrentSession();
		User user = (User) session.get(User.class, username);
		return user;
	}

}
