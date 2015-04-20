package com.excilys.cdb.dao;

import java.util.List;

import com.excilys.cdb.model.Company;

public interface ICompanyDAO {
	List<Company> findAll();

	Company find(long id);
}
