package com.excilys.cdb.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		PreparedStatement state = null;
		Connection conn = null;
		try {
			conn = FactoryConnection.INSTANCE.openConnection();
			conn.setAutoCommit(false);
			ComputerDAO.INSTANCE.deleteByCompany(company_id, conn);
			CompanyDAO.INSTANCE.delete(company_id, conn);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ServiceException();
			}
			throw new ServiceException();
		} finally {
			FactoryConnection.INSTANCE.closeConnection(conn, state);
		}

	}

}
