package main.java.com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import main.java.com.excilys.cdb.mapper.Mapper;
import main.java.com.excilys.cdb.model.Computer;

public enum ComputerDAO implements IComputerDAO {
	INSTANCE;
	/**
	 * Instantiates a new dao.
	 *
	 * @param conn
	 *            the connection
	 */

	@Override
	public void create(Computer obj) {
		Connection connect = FactoryConnection.INSTANCE.openConnection();
		PreparedStatement state = null;
		try {

			String name = obj.getName();
			Timestamp introduced = Mapper.LocalDateTimeToTimestamp(obj
					.getIntro_date());
			Timestamp discontinued = Mapper.LocalDateTimeToTimestamp(obj
					.getDis_date());
			long id_company = obj.getCompany().getId();
			state = connect
					.prepareStatement("INSERT INTO computer(name,introduced,discontinued,company_id) VALUES (?,?,?,?)");
			state.setString(1, name);
			state.setTimestamp(2, introduced);
			state.setTimestamp(3, discontinued);
			state.setLong(4, id_company);
			state.executeUpdate();
		} catch (SQLException e) {
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
			Timestamp introduced = Mapper.LocalDateTimeToTimestamp(obj
					.getIntro_date());
			Timestamp discontinued = Mapper.LocalDateTimeToTimestamp(obj
					.getDis_date());
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
			return Mapper.ResultSetToComputer(result);
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

			return Mapper.getComputers(result);
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
			return Mapper.getComputers(result);
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			FactoryConnection.INSTANCE.closeConnection(connect, state, result);
		}
	}
}
