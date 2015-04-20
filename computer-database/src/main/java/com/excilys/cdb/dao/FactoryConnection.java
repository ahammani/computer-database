package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.excilys.cdb.database.DatabaseNaming.*;

public enum FactoryConnection {
	INSTANCE;

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection openConnection() {
		try {
			return DriverManager.getConnection(URL, USER, PWD);
		} catch (SQLException e) {
			throw new DAOException();
		}
	}

	public void closeConnection(Connection c) {
		try {
			if (c != null)
				c.close();
		} catch (SQLException e) {
			throw new DAOException();
		}
	}

	public void closeConnection(Connection c, PreparedStatement p) {
		try {
			if (c != null)
				c.close();
			if (p != null)
				p.close();
		} catch (SQLException e) {
			throw new DAOException();
		}
	}

	public void closeConnection(Connection c, PreparedStatement p, ResultSet r) {
		try {
			if (c != null)
				c.close();
			if (p != null)
				p.close();
			if (r != null)
				r.close();
		} catch (SQLException e) {
			throw new DAOException();
		}
	}

}
