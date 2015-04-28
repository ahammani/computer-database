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

public enum ConnectionFactory {
	INSTANCE;

	private InputStream input;
	private Properties prop = new Properties();
	private BoneCP connectionPool = null;
	private final ThreadLocal<Connection> CONNECTION = new ThreadLocal<Connection>();

	ConnectionFactory() {
		try {
			input = this.getClass().getClassLoader()
					.getResourceAsStream("config.properties");
			prop.load(input);
			Class.forName(prop.getProperty("driver"));

			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(prop.getProperty("url"));
			config.setLazyInit(true);
			config.setUsername(prop.getProperty("user"));
			config.setPassword(prop.getProperty("pwd"));
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
			throw new DAOException(e);
		}
	}

	public Connection getConnection() {
		try {
			if (CONNECTION.get() == null || CONNECTION.get().isClosed()) {
				CONNECTION.set(connectionPool.getConnection());
			}
			return CONNECTION.get();
		} catch (SQLException e) {
			e.getMessage();
			throw new DAOException(e);
		}
	}

	public void closeConnection() {
		Connection c = CONNECTION.get();
		try {
			if (c != null && c.getAutoCommit())
				c.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		CONNECTION.remove();
	}

	public void closeConnection(PreparedStatement p) {
		try {
			if (p != null)
				p.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		closeConnection();
	}

	public void closeConnection(PreparedStatement p, ResultSet r) {
		try {
			if (r != null)
				r.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		closeConnection(p);
	}

	public void startTransaction() {
		Connection conn = CONNECTION.get();
		try {
			if (conn != null)
				conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public void commit() {
		Connection conn = CONNECTION.get();
		try {
			if (conn != null)
				conn.commit();
		} catch (SQLException e) {
			e.getMessage();
			throw new DAOException(e);
		}
	}

	public void rollback() {
		Connection conn = CONNECTION.get();
		try {
			if (conn != null)
				conn.rollback();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
