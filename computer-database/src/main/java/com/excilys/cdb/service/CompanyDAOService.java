package com.excilys.cdb.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.dao.FactoryConnection;
import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Company;

public enum CompanyDAOService {
	INSTANCE;

	public Company getCompany(long id) {
		return CompanyDAO.INSTANCE.find(id);
	}

	public List<Company> getAll() {
		return CompanyDAO.INSTANCE.findAll();
	}

	public void deleteCompany(long company_id) {
		try {
			FactoryConnection.INSTANCE.startTransaction();
			ComputerDAO.INSTANCE.deleteByCompany(company_id);
			CompanyDAO.INSTANCE.delete(company_id);
		} catch (SQLException e) {
			FactoryConnection.INSTANCE.rollback();
			throw new ServiceException();
		} finally {
			FactoryConnection.INSTANCE.closeConnection();
		}

	}

}
