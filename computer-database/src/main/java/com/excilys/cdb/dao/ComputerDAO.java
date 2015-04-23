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

	private static final String FIND_ALL = "SELECT computer.id as c_id,computer.name as c_name,introduced,discontinued,company_id,company.name FROM computer LEFT OUTER JOIN company  on computer.company_id=company.id";

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

	public int count(String search) {
		Connection connect = FactoryConnection.INSTANCE.openConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			state = connect
					.prepareStatement("SELECT COUNT(*) FROM computer LEFT OUTER JOIN company  on computer.company_id=company.id"
							+ " WHERE computer.name LIKE ? OR company.name LIKE ? ");
			state.setString(1, "%" + search + "%");
			state.setString(2, "%" + search + "%");
			result = state.executeQuery();
			if (result.next())
				return result.getInt("COUNT(*)");
			else
				return 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
			Timestamp introduced = SQLMapper.LocalDateToTimestamp(obj
					.getIntroduced());
			Timestamp discontinued = SQLMapper.LocalDateToTimestamp(obj
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
			Timestamp introduced = SQLMapper.LocalDateToTimestamp(obj
					.getIntroduced());
			Timestamp discontinued = SQLMapper.LocalDateToTimestamp(obj
					.getDiscontinued());
			Company company = obj.getCompany();
			long id_company = company.getId();
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
			System.out.println("UPDATE name : " + name + " intro : "
					+ introduced + " dis :" + discontinued + " company_id :"
					+ id_company + " computer_id : " + id);
			state.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
			state = connect.prepareStatement(FIND_ALL);
			result = state.executeQuery();

			return SQLMapper.getComputers(result);
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			FactoryConnection.INSTANCE.closeConnection(connect, state, result);
		}

	}

	@Override
	public List<Computer> findAll(int offset, int limit, String field_order,
			String order) {

		Connection connect = FactoryConnection.INSTANCE.openConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		if (field_order.isEmpty())
			field_order = "c_id";
		if (order.isEmpty())
			order = "ASC";

		System.out.println("FIELD" + field_order + " ORDER " + order);
		try {
			state = connect.prepareStatement(FIND_ALL + " ORDER BY "
					+ field_order + " " + order + " LIMIT ? OFFSET ? ");
			state.setInt(1, limit);
			state.setInt(2, offset);
			System.out.println("STATE " + state.toString());
			result = state.executeQuery();
			return SQLMapper.getComputers(result);
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			FactoryConnection.INSTANCE.closeConnection(connect, state, result);
		}
	}

	public List<Computer> findAll(String search, int offset, int limit,
			String field_order, String order) {
		Connection connect = FactoryConnection.INSTANCE.openConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		if (field_order.isEmpty())
			field_order = "c_id";
		if (order.isEmpty())
			order = "ASC";
		try {
			state = connect.prepareStatement(FIND_ALL
					+ " WHERE computer.name LIKE ? OR company.name LIKE ? "
					+ "  ORDER BY " + field_order + " " + order
					+ " LIMIT ?  OFFSET ? ");
			state.setString(1, "%" + search + "%");
			state.setString(2, "%" + search + "%");
			state.setInt(3, limit);
			state.setInt(4, offset);
			result = state.executeQuery();
			return SQLMapper.getComputers(result);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DAOException();
		} finally {
			FactoryConnection.INSTANCE.closeConnection(connect, state, result);
		}
	}

	/**
	 * Find all computers with company_id equal id
	 * 
	 * @param id
	 * @return
	 */
	public List<Computer> findAllCompany(long company_id) {
		Connection connect = FactoryConnection.INSTANCE.openConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			state = connect.prepareStatement(FIND_ALL + " WHERE company_id=?");
			state.setLong(1, company_id);
			result = state.executeQuery();
			return SQLMapper.getComputers(result);
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			FactoryConnection.INSTANCE.closeConnection(connect, state, result);
		}
	}

	public void deleteByCompany(long company_id, Connection connect)
			throws SQLException {
		PreparedStatement state = null;
		state = connect
				.prepareStatement("DELETE FROM computer WHERE company_id=?");
		state.setLong(1, company_id);
		state.executeUpdate();
	}
}
