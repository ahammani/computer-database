package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import com.excilys.cdb.exception.ConnectionException;

@Component
public class ConnectionFactory {

	private final Logger logger = LoggerFactory
			.getLogger(ConnectionFactory.class);
	@Autowired
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}

	public void closeConnection() {
		try {
			getConnection().close();
		} catch (SQLException e) {
			throw new ConnectionException();
		}
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

}
