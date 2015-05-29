package com.excilys.cdb.dao;

import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.exception.IllegalCallException;

/**
 * The DAO interface. DAO interact with database by SQL request
 * 
 */
public interface IDAO<T> {

	/**
	 * Creates a new T in DB with obj's field.
	 *
	 * @param obj
	 *            the T
	 * @return the new Ts'id
	 */
	default void create(T obj) {
		throw new IllegalCallException();
	};

	/**
	 * Delete a T with the id id.
	 *
	 * @param id
	 */
	void delete(long id) throws SQLException;

	/**
	 * Update a T
	 *
	 * @param obj
	 *            the object
	 */
	default void update(T obj) {
		throw new IllegalCallException();
	};

	/**
	 * Find an T by his id.
	 *
	 * @param id
	 *            T id
	 * @return the T found or null if doesn't
	 */
	T find(long id);

	/**
	 * Gets the list of all T.
	 * 
	 * @return the T list
	 */
	List<T> findAll();

}
