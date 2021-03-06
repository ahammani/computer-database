package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.Page;

/**
 * The Interface IComputerDAOService.
 */
public interface IComputerService {

	/**
	 * Get all computers.
	 *
	 * @return a list of all computers
	 */
	List<Computer> getAll();

	/**
	 * Get all computers .
	 *
	 * @return a list of all computers
	 */
	List<Computer> getAll(Page page);

	/**
	 * Get the computer.
	 *
	 * @param id
	 *            the comuters'id
	 * @return the computer
	 */
	Computer getComputer(long id);

	/**
	 * Add a computer.
	 *
	 * @param c
	 *            the computer
	 * 
	 */
	void addComputer(Computer c);

	/**
	 * Update a computer.
	 *
	 * @param c
	 */
	void updateComputer(Computer c);

	/**
	 * Delete computer.
	 *
	 * @param id
	 *            the id
	 */
	void deleteComputer(long id);

	/**
	 * Count total of computers.
	 *
	 * @return the number of computers in db
	 */
	int count();

	/**
	 * Count total of computers with the field search
	 *
	 * @param search
	 *            the search
	 * @return the number of computers in db
	 */
	int count(String search);
}
