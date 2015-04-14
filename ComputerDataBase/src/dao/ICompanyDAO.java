package dao;

import java.util.List;

import model.Company;

public interface ICompanyDAO {
	public abstract List<Company> findAll();
}
