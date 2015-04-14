package dao;

import java.util.List;

import model.Computer;

/**
 * The Class DAO. DAO interact with database by SQL request
 * 
 * @param <T>
 *            the generic type
 */
public interface IComputerDAO {

	/**
	 * Creates a new raw in DB with obj's field.
	 *
	 * @param obj
	 *            the object
	 */
	void create(Computer obj);

	/**
	 * Delete a raw with the id id.
	 *
	 * @param id
	 *            the id
	 */
	void delete(long id);

	/**
	 * Update a raw.
	 *
	 * @param obj
	 *            the object
	 */
	void update(Computer obj);

	/**
	 * Find an object by his id.
	 *
	 * @param id
	 *            the id
	 * @return an object T
	 */
	Computer find(long id);

	/**
	 * Gets the list of all T elements. A raw represents one elements
	 * 
	 * @return the list
	 */
	List<Computer> findAll();

	List<Computer> findAll(int offset, int limit);

}
