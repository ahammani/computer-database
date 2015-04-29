package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.model.Company;

public enum CompanyDAO implements IDAO<Company> {
	INSTANCE;
	private final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

	@Override
	public Company find(long id) {
		Connection connect = ConnectionFactory.INSTANCE.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			state = connect
					.prepareStatement("SELECT * FROM company WHERE id=?");
			state.setLong(1, id);
			result = state.executeQuery();
			return SQLMapper.resultSetToCompany(result);
		} catch (SQLException e) {
			logger.error("Error on find with ID={}", id);
			throw new DAOException(e);
		} finally {
			ConnectionFactory.INSTANCE.closeConnection(state, result);
		}
	}

	@Override
	public List<Company> findAll() {
		Connection connect = ConnectionFactory.INSTANCE.getConnection();
		PreparedStatement state = null;
		List<Company> l = new ArrayList<>();
		try {
			state = connect
					.prepareStatement("SELECT DISTINCT * FROM company ORDER BY name ASC");
			ResultSet result = state.executeQuery();

			while (result.next()) {
				long id = result.getLong("id");
				String s = result.getString("name");
				Company obj = new Company(id, s);
				l.add(obj);
			}
			return l;
		} catch (SQLException e) {
			logger.error("Error on findAll !");
			throw new DAOException(e);
		} finally {
			ConnectionFactory.INSTANCE.closeConnection(state);
		}

	}

	@Override
	public void delete(long company_id) throws SQLException {
		Connection connect = ConnectionFactory.INSTANCE.getConnection();
		PreparedStatement state = null;
		state = connect.prepareStatement("DELETE FROM company WHERE id=?");
		state.setLong(1, company_id);
		state.executeUpdate();
		if (state != null)
			state.close();

	}

}
