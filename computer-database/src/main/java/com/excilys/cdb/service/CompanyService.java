package com.excilys.cdb.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.dao.ConnectionFactory;
import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Company;

@Service
public class CompanyService implements ICompanyService {
	@Autowired
	private ComputerDAO computerDAO;
	@Autowired
	private CompanyDAO companyDAO;
	@Autowired
	private ConnectionFactory connectionFactory;

	Logger logger = LoggerFactory.getLogger(CompanyService.class);

	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}

	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}

	@Override
	public Company getCompany(long id) {
		return companyDAO.find(id);
	}

	@Override
	public List<Company> getAll() {
		return companyDAO.findAll();
	}

	@Override
	public void deleteCompany(long company_id) {
		try {
			connectionFactory.startTransaction();
			computerDAO.deleteByCompany(company_id);
			companyDAO.delete(company_id);
			connectionFactory.commit();
			connectionFactory.closeConnection();
		} catch (SQLException e) {
			connectionFactory.rollback();
			logger.error("deleteCompany on CompanyService error !");
			throw new ServiceException(e);
		} finally {
			connectionFactory.closeConnection();
		}

	}

}
