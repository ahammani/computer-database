package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.model.Company;

public enum CompanyDAO implements ICompanyDAO {
	INSTANCE;

	@Override
	public Company find(long id) {
		Connection connect = FactoryConnection.INSTANCE.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			state = connect
					.prepareStatement("SELECT * FROM company WHERE id=?");
			state.setLong(1, id);
			result = state.executeQuery();
			return SQLMapper.ResultSetToCompany(result);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			FactoryConnection.INSTANCE.closeConnection(state, result);
		}
	}

	@Override
	public List<Company> findAll() {
		Connection connect = FactoryConnection.INSTANCE.getConnection();
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
			throw new DAOException(e);
		} finally {
			FactoryConnection.INSTANCE.closeConnection(state);
		}

	}

	@Override
	public void delete(Long company_id) throws SQLException {
		Connection connect = FactoryConnection.INSTANCE.getConnection();
		PreparedStatement state = null;
		state = connect.prepareStatement("DELETE FROM company WHERE id=?");
		state.setLong(1, company_id);
		state.executeUpdate();
		if (state != null)
			state.close();

	}

}
