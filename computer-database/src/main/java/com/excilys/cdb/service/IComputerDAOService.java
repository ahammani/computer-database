package com.excilys.cdb.service;

import java.util.List;

import page.Page;

import com.excilys.cdb.model.Computer;

/**
 * The Interface IComputerDAOService.
 */
public interface IComputerDAOService {

	/**
	 * Get all computers.
	 *
	 * @return a list of all computers
	 */
	List<Computer> getAll();

	/**
	 * Get all computers which contains all parameters .
	 *
	 * @param pages
	 * @param field_order
	 * @param order
	 * @return a list of all computers
	 */
	List<Computer> getAll(Page pages, String field_order, String order);

	/**
	 * Get all computers which contains all parameters .
	 *
	 * @param search
	 * @param pages
	 * @param field_order
	 * @param order
	 * @return the all
	 */
	List<Computer> getAll(String search, Page pages, String field_order,
			String order);

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
	 * @return the computers id
	 * 
	 */
	int addComputer(Computer c);

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
