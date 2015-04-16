package com.excilys.cdb.dao;

import java.util.List;

import com.excilys.cdb.model.Company;

public interface ICompanyDAO {
	public abstract List<Company> findAll();
}
