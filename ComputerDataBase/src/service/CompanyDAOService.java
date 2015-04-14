package service;

import java.util.List;

import model.Company;
import dao.CompanyDAO;

public class CompanyDAOService {
	private CompanyDAO companies = new CompanyDAO();

	public CompanyDAO getCompanies() {
		return companies;
	}

	public void setCompanies(CompanyDAO companies) {
		this.companies = companies;
	}

	public List<Company> getAll() {
		return companies.findAll();
	}
}
