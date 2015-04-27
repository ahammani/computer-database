package com.excilys.cdb.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.dao.FactoryConnection;
import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Company;

public enum CompanyDAOService implements ICompanyDAOService {
	INSTANCE;

	@Override
	public Company getCompany(long id) {
		return CompanyDAO.INSTANCE.find(id);
	}

	@Override
	public List<Company> getAll() {
		return CompanyDAO.INSTANCE.findAll();
	}

	@Override
	public void deleteCompany(long company_id) {
		try {
			FactoryConnection.INSTANCE.startTransaction();
			ComputerDAO.INSTANCE.deleteByCompany(company_id);
			CompanyDAO.INSTANCE.delete(company_id);
			FactoryConnection.INSTANCE.commit();
			FactoryConnection.INSTANCE.endTransaction();
			FactoryConnection.INSTANCE.closeConnection();
		} catch (SQLException e) {
			FactoryConnection.INSTANCE.rollback();
			throw new ServiceException(e);
		} finally {
			FactoryConnection.INSTANCE.closeConnection();
		}

	}

}
