package data;

import java.sql.Connection;
import java.util.List;

/**
 * The Class DAO. DAO interact with database by SQL request
 * 
 * @param <T>
 *            the generic type
 */
public abstract class DAO<T> {

	/** The Database connection. */
	protected Connection connect;

	/**
	 * Instantiates a new dao.
	 *
	 * @param conn
	 *            the connection
	 */
	public DAO(Connection conn) {
		this.connect = conn;
	}

	/**
	 * Creates a new raw in DB with obj's field.
	 *
	 * @param obj
	 *            the object
	 */
	public abstract void create(T obj);

	/**
	 * Delete a raw with the id id.
	 *
	 * @param id
	 *            the id
	 */
	public abstract void delete(int id);

	/**
	 * Update a raw.
	 *
	 * @param obj
	 *            the object
	 */
	public abstract void update(T obj);

	/**
	 * Find an object by his id.
	 *
	 * @param id
	 *            the id
	 * @return an object T
	 */
	public abstract T find(int id);

	/**
	 * Gets the list of all T elements. A raw represents one elements
	 * 
	 * @return the list
	 */
	public abstract List<T> getList();
}
