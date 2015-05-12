package com.excilys.cdb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.model.Company;

@Service
public class CompanyService implements ICompanyService {
	@Autowired
	private ComputerDAO computerDAO;
	@Autowired
	private CompanyDAO companyDAO;

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
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteCompany(long company_id) {
		computerDAO.deleteByCompany(company_id);
		companyDAO.delete(company_id);
	}

}
