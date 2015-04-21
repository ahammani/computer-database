package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public enum ComputerDAO implements IComputerDAO {
	INSTANCE;
	/**
	 * Instantiates a new dao.
	 *
	 * @param conn
	 *            the connection
	 */

	public int count() {
		Connection connect = FactoryConnection.INSTANCE.openConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			state = connect.prepareStatement("SELECT COUNT(*) FROM computer");
			result = state.executeQuery();
			if (result.next())
				return result.getInt("COUNT(*)");
			else
				return 0;
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			FactoryConnection.INSTANCE.closeConnection(connect, state, result);
		}
	}

	@Override
	public int create(Computer obj) {
		Connection connect = FactoryConnection.INSTANCE.openConnection();
		PreparedStatement state = null;
		try {
			long id_company;
			String name = obj.getName();
			Timestamp introduced = SQLMapper.LocalDateTimeToTimestamp(obj
					.getIntroduced());
			Timestamp discontinued = SQLMapper.LocalDateTimeToTimestamp(obj
					.getDiscontinued());
			Company company = obj.getCompany();

			if (company != null)
				id_company = company.getId();
			else
				id_company = 0;

			state = connect
					.prepareStatement(
							"INSERT INTO computer(name,introduced,discontinued,company_id) VALUES (?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
			System.out.println();
			state.setString(1, name);
			state.setTimestamp(2, introduced);
			state.setTimestamp(3, discontinued);
			if (id_company == 0) {
				state.setNull(4, Types.NULL);
			} else {
				state.setLong(4, id_company);
			}
			state.executeUpdate();

			ResultSet rs = state.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return -1;
		} catch (SQLException e) {
			System.out.println(obj.toString());
			throw new DAOException();
		} finally {
			FactoryConnection.INSTANCE.closeConnection(connect, state);
		}

	}

	@Override
	public void delete(long id) {
		Connection connect = FactoryConnection.INSTANCE.openConnection();
		PreparedStatement state = null;
		try {
			state = connect.prepareStatement("DELETE FROM computer WHERE id=?");
			state.setLong(1, id);
			state.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			FactoryConnection.INSTANCE.closeConnection(connect, state);
		}

	}

	@Override
	public void update(Computer obj) {
		Connection connect = FactoryConnection.INSTANCE.openConnection();
		PreparedStatement state = null;
		try {
			String name = obj.getName();
			Timestamp introduced = SQLMapper.LocalDateTimeToTimestamp(obj
					.getIntroduced());
			Timestamp discontinued = SQLMapper.LocalDateTimeToTimestamp(obj
					.getDiscontinued());
			long id_company = obj.getCompany().getId();
			long id = obj.getId();

			state = connect
					.prepareStatement("UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?");
			state.setString(1, name);
			state.setTimestamp(2, introduced);
			state.setTimestamp(3, discontinued);
			if (id_company == 0)
				state.setNull(4, Types.NULL);
			else
				state.setLong(4, id_company);
			state.setLong(5, id);
			state.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			FactoryConnection.INSTANCE.closeConnection(connect, state);
		}
	}

	@Override
	public Computer find(long id) {
		Connection connect = FactoryConnection.INSTANCE.openConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			state = connect
					.prepareStatement("SELECT computer.id as c_id,computer.name as c_name,introduced,discontinued,company_id,company.name FROM computer LEFT OUTER JOIN company  on computer.company_id=company.id WHERE computer.id=?");
			state.setLong(1, id);
			result = state.executeQuery();
			return SQLMapper.ResultSetToComputer(result);
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			FactoryConnection.INSTANCE.closeConnection(connect, state, result);
		}
	}

	@Override
	public List<Computer> findAll() {
		Connection connect = FactoryConnection.INSTANCE.openConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			state = connect
					.prepareStatement("SELECT computer.id as c_id,computer.name as c_name,introduced,discontinued,company_id,company.name FROM computer LEFT OUTER JOIN company  on computer.company_id=company.id");
			result = state.executeQuery();

			return SQLMapper.getComputers(result);
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			FactoryConnection.INSTANCE.closeConnection(connect, state, result);
		}

	}

	@Override
	public List<Computer> findAll(int offset, int limit) {
		Connection connect = FactoryConnection.INSTANCE.openConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			state = connect
					.prepareStatement("SELECT computer.id as c_id,computer.name as c_name,introduced,discontinued,company_id,company.name FROM computer LEFT OUTER JOIN company  on computer.company_id=company.id LIMIT ? OFFSET ?");
			state.setInt(1, limit);
			state.setInt(2, offset);
			result = state.executeQuery();
			return SQLMapper.getComputers(result);
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			FactoryConnection.INSTANCE.closeConnection(connect, state, result);
		}
	}
}
