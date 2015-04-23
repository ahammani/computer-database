package com.excilys.cdb.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.cdb.exception.DAOException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public enum FactoryConnection {
	INSTANCE;

	private InputStream input;
	private Properties prop = new Properties();
	private BoneCP connectionPool = null;

	FactoryConnection() {
		try {
			input = this.getClass().getClassLoader()
					.getResourceAsStream("config.properties");
			prop.load(input);
			Class.forName(prop.getProperty("driver"));

			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(getProp("url"));
			config.setUsername(getProp("user"));
			config.setPassword(getProp("pwd"));
			config.setMinConnectionsPerPartition(1);
			config.setMaxConnectionsPerPartition(5);
			config.setPartitionCount(2);
			connectionPool = new BoneCP(config);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			throw new DAOException();
		}
	}

	public String getProp(String s) {
		return prop.getProperty(s);
	}

	public Connection openConnection() {
		try {
			return connectionPool.getConnection();
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
