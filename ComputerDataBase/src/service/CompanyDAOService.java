package service;

import java.util.List;

import model.Company;
import dao.CompanyDAO;

public enum CompanyDAOService {
	INSTANCE;

	public List<Company> getAll() {
		return CompanyDAO.INSTANCE.findAll();
	}
}
