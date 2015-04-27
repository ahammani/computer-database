package com.excilys.cdb.dao;

import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.model.Company;

/**
 * The Company DAO Interface. DAO interact with database by SQL request
 * 
 */
public interface ICompanyDAO {
	/**
	 * Get the list of all company.
	 * 
	 * @return the company list
	 */
	List<Company> findAll();

	/**
	 * Find an company by his id.
	 *
	 * @param id
	 *            company id
	 * @return the company found or null if doesn't exist
	 */
	Company find(long id);

	/**
	 * Delete a company with the id id.
	 *
	 * @param id
	 */
	void delete(Long company_id) throws SQLException;
}
