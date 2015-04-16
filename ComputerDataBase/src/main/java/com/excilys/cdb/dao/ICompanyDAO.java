package main.java.com.excilys.cdb.dao;

import java.util.List;

import main.java.com.excilys.cdb.model.Company;

public interface ICompanyDAO {
	public abstract List<Company> findAll();
}
