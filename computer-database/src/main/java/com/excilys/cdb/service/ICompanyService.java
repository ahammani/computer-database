package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Company;

/**
 * The Interface ICompanyDAOService.
 */
public interface ICompanyService {

	/**
	 * Get the company with the id id.
	 *
	 * @param id
	 * @return the company
	 */
	Company getCompany(long id);

	/**
	 * Get all companies.
	 *
	 * @return a list of all companies
	 */
	List<Company> getAll();

	/**
	 * Delete a company.
	 *
	 * @param company_id
	 *            the id company
	 */
	void deleteCompany(long company_id);
}
