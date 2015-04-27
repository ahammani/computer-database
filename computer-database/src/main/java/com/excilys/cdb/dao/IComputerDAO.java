package com.excilys.cdb.dao;

import java.util.List;

import com.excilys.cdb.model.Computer;

/**
 * The Computer DAO interface. DAO interact with database by SQL request
 * 
 */
public interface IComputerDAO {

	/**
	 * Creates a new computer in DB with obj's field.
	 *
	 * @param obj
	 *            the computer
	 * @return the new computers'id
	 */
	int create(Computer obj);

	/**
	 * Delete a computer with the id id.
	 *
	 * @param id
	 */
	void delete(long id);

	/**
	 * Update a computer
	 *
	 * @param obj
	 *            the object
	 */
	void update(Computer obj);

	/**
	 * Find an computer by his id.
	 *
	 * @param id
	 *            computers id
	 * @return the computer found or null if doesn't
	 */
	Computer find(long id);

	/**
	 * Gets the list of all computers.
	 * 
	 * @return the computers list
	 */
	List<Computer> findAll();

	List<Computer> findAll(int offset, int limit, String field_order,
			String order);

}
