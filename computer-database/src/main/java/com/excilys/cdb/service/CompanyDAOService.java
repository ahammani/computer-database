package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.model.Company;

public enum CompanyDAOService {
	INSTANCE;

	public Company getCompany(long id) {
		return CompanyDAO.INSTANCE.find(id);
	}

	public List<Company> getAll() {
		return CompanyDAO.INSTANCE.findAll();
	}

}
