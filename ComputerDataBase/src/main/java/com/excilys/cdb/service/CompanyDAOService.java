package main.java.com.excilys.cdb.service;

import java.util.List;

import main.java.com.excilys.cdb.dao.CompanyDAO;
import main.java.com.excilys.cdb.model.Company;

public enum CompanyDAOService {
	INSTANCE;

	public List<Company> getAll() {
		return CompanyDAO.INSTANCE.findAll();
	}
}
