package com.excilys.cdb.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.cdb.exception.DAOException;

public enum FactoryConnection {
	INSTANCE;

	private InputStream input;
	private Properties prop = new Properties();

	FactoryConnection() {
		try {
			input = this.getClass().getClassLoader()
					.getResourceAsStream("config.properties");
			prop.load(input);
			Class.forName(prop.getProperty("driver"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getProp(String s) {
		return FactoryConnection.INSTANCE.prop.getProperty(s);
	}

	public Connection openConnection() {
		try {
			return DriverManager.getConnection(
					FactoryConnection.INSTANCE.getProp("url"),
					FactoryConnection.INSTANCE.getProp("user"),
					FactoryConnection.INSTANCE.getProp("pwd"));
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
			if (p != null)
				p.close();
		} catch (SQLException e) {
			throw new DAOException();
		}
		closeConnection(c);
	}

	public void closeConnection(Connection c, PreparedStatement p, ResultSet r) {
		try {
			if (r != null)
				r.close();
		} catch (SQLException e) {
			throw new DAOException();
		}
		closeConnection(c, p);
	}

}
