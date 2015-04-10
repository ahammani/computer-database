package data;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {
	protected Connection connect;

	public DAO(Connection conn) {
		this.connect = conn;
	}

	public abstract void create(T obj);

	public abstract void delete(T obj);

	public abstract void update(T obj);

	public abstract T find(int id);

	public abstract List<T> getList();
}
