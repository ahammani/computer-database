package com.excilys.cdb.dao;

import com.excilys.cdb.model.User;

public interface IUserDAO {
	User findByUserName(String username);
}
