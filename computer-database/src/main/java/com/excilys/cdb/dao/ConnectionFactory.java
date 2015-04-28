package com.excilys.cdb.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exception.ConnectionException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public enum ConnectionFactory {
	INSTANCE;

	private InputStream input;
	private Properties prop = new Properties();
	private BoneCP connectionPool = null;
	private final ThreadLocal<Connection> CONNECTION = new ThreadLocal<Connection>();
	private final Logger logger = LoggerFactory
			.getLogger(ConnectionFactory.class);

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
		} catch (IOException | ClassNotFoundException | SQLException e) {
			logger.error("Error on ConnectionFactory constructor");
			throw new ConnectionException();
		}
	}

	public Connection getConnection() {
		try {
			if (CONNECTION.get() == null || CONNECTION.get().isClosed()) {
				CONNECTION.set(connectionPool.getConnection());
			}
			return CONNECTION.get();
		} catch (SQLException e) {
			logger.error("SQLError on getConnection");
			throw new ConnectionException();
		}
	}

	public void closeConnection() {
		Connection c = CONNECTION.get();
		try {
			if (c != null && c.getAutoCommit())
				c.close();
		} catch (SQLException e) {
			logger.error("SQLError on closeConnection");
			throw new ConnectionException();
		}
		CONNECTION.remove();
	}

	public void closeConnection(PreparedStatement p) {
		try {
			if (p != null)
				p.close();
		} catch (SQLException e) {
			logger.error("SQLError on closeConnection with preparedStatement");
			throw new ConnectionException();
		}
		closeConnection();
	}

	public void closeConnection(PreparedStatement p, ResultSet r) {
		try {
			if (r != null)
				r.close();
		} catch (SQLException e) {
			logger.error("SQLError on closeConnection with ResultSet");
			throw new ConnectionException();
		}
		closeConnection(p);
	}

	public void startTransaction() {
		Connection conn = CONNECTION.get();
		try {
			if (conn != null)
				conn.setAutoCommit(false);
		} catch (SQLException e) {
			logger.error("SQLError on startTransaction");
			throw new ConnectionException();
		}
	}

	public void commit() {
		Connection conn = CONNECTION.get();
		try {
			if (conn != null)
				conn.commit();
		} catch (SQLException e) {
			logger.error("SQLError on commit");
			throw new ConnectionException();
		}
	}

	public void rollback() {
		Connection conn = CONNECTION.get();
		try {
			if (conn != null)
				conn.rollback();
		} catch (SQLException e) {
			logger.error("SQLError on rollback");
			throw new ConnectionException();
		}
	}

}
