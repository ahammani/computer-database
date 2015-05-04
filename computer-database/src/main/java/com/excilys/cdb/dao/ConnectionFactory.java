package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.excilys.cdb.exception.ConnectionException;

@Component
public class ConnectionFactory {

	private final ThreadLocal<Connection> CONNECTION = new ThreadLocal<Connection>();
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
		logger.info("In getConnection");
		try {
			if (CONNECTION.get() == null || CONNECTION.get().isClosed()) {
				// ApplicationContext context = new
				// ClassPathXmlApplicationContext(
				// "classpath:application-context.xml");
				// dataSource = (DataSource) context.getBean("dataSource");
				Connection c = dataSource.getConnection();
				if (c == null) {
					System.out.println("NULL");
				}
				CONNECTION.set(c);
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
