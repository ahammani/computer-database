package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

@Repository
public class ComputerDAO implements IDAO<Computer> {
	@Autowired
	private ConnectionFactory connectionFactory;

	private static final String FIND_ALL = "SELECT computer.id as c_id,computer.name as c_name,introduced,discontinued,company_id,company.name FROM computer LEFT OUTER JOIN company  on computer.company_id=company.id";
	private final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	public int count() {
		Connection connect = connectionFactory.getConnection();
		Statement state = null;
		ResultSet result = null;
		try {
			state = connect.createStatement();
			result = state.executeQuery("SELECT COUNT(*) FROM computer");
			if (result.next()) {
				int res = result.getInt("COUNT(*)");
				logger.debug("Count done. {} computers found", res);
				return res;
			} else
				return 0;
		} catch (SQLException e) {
			logger.error("Error on count !");
			throw new DAOException(e);
		} finally {
			try {
				if (state != null) {
					state.close();
				}
				if (result != null) {
					result.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}
			connectionFactory.closeConnection();
		}
	}

	public int count(String search) {
		Connection connect = connectionFactory.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			state = connect
					.prepareStatement("SELECT COUNT(*) FROM computer LEFT OUTER JOIN company  on computer.company_id=company.id"
							+ " WHERE computer.name LIKE ? OR company.name LIKE ? ");
			state.setString(1, "%" + search + "%");
			state.setString(2, "%" + search + "%");
			result = state.executeQuery();
			if (result.next()) {
				int res = result.getInt("COUNT(*)");
				logger.debug("Count(search) done. {} computers found", res);
				return res;
			}
			return 0;
		} catch (SQLException e) {
			logger.error("Error on search count !");
			throw new DAOException(e);
		} finally {
			connectionFactory.closeConnection(state, result);
		}
	}

	@Override
	public int create(Computer obj) {
		Connection connect = connectionFactory.getConnection();
		PreparedStatement state = null;
		try {
			long id_company;
			String name = obj.getName();
			Timestamp introduced = SQLMapper.localdateToTimestamp(obj
					.getIntroduced());
			Timestamp discontinued = SQLMapper.localdateToTimestamp(obj
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
				int res = rs.getInt(1);
				logger.debug("Creation done. computer's id {} ", res);
				return res;
			}
			return -1;
		} catch (SQLException e) {
			logger.error("Error on create OBJ : {}", obj.toString());
			throw new DAOException(e);
		} finally {
			connectionFactory.closeConnection(state);
		}

	}

	@Override
	public void delete(long id) {
		Connection connect = connectionFactory.getConnection();
		PreparedStatement state = null;
		try {
			state = connect.prepareStatement("DELETE FROM computer WHERE id=?");
			state.setLong(1, id);
			state.executeUpdate();
			logger.debug("Deletion done.Computers'id deleted :{}", id);
		} catch (SQLException e) {
			logger.error("Error on delete with id={} !", id);
			throw new DAOException(e);
		} finally {
			connectionFactory.closeConnection(state);
		}

	}

	@Override
	public void update(Computer obj) {
		Connection connect = connectionFactory.getConnection();
		PreparedStatement state = null;
		try {
			String name = obj.getName();
			Timestamp introduced = SQLMapper.localdateToTimestamp(obj
					.getIntroduced());
			Timestamp discontinued = SQLMapper.localdateToTimestamp(obj
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
			state.executeUpdate();
			logger.debug(
					"Computer update done.ID:{} NAME:{} INTRO:{} DIS:{} ID_COMPANY:{}",
					id, name, introduced, discontinued, id_company);
		} catch (SQLException e) {
			logger.error("Error on update  with ID:{} NAME:{} INTRO:{} DIS:{}",
					obj.getId(), obj.getName(), obj.getIntroduced(),
					obj.getDiscontinued());
			throw new DAOException(e);
		} finally {
			connectionFactory.closeConnection(state);
		}
	}

	@Override
	public Computer find(long id) {
		Connection connect = connectionFactory.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			state = connect
					.prepareStatement("SELECT computer.id as c_id,computer.name as c_name,introduced,discontinued,company_id,company.name FROM computer LEFT OUTER JOIN company  on computer.company_id=company.id WHERE computer.id=?");
			state.setLong(1, id);
			result = state.executeQuery();
			logger.debug("Computer with id {} found", id);
			return SQLMapper.resultSetToComputer(result);
		} catch (SQLException e) {
			logger.error("Error on computer with id {} !", id);
			throw new DAOException(e);
		} finally {
			connectionFactory.closeConnection(state, result);
		}
	}

	@Override
	public List<Computer> findAll() {
		Connection connect = connectionFactory.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			state = connect.prepareStatement(FIND_ALL);
			result = state.executeQuery();

			logger.debug("FindAll done.");
			return SQLMapper.getComputers(result);
		} catch (SQLException e) {
			logger.error("Error on findAll !");
			throw new DAOException(e);
		} finally {
			connectionFactory.closeConnection(state, result);
		}

	}

	public List<Computer> findAll(int offset, int limit, String field_order,
			String order) {
		Connection connect = connectionFactory.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		if (field_order.isEmpty())
			field_order = "c_id";
		if (order.isEmpty())
			order = "ASC";

		try {
			state = connect.prepareStatement(FIND_ALL + " ORDER BY "
					+ field_order + " " + order + " LIMIT ? OFFSET ? ");
			state.setInt(1, limit);
			state.setInt(2, offset);
			result = state.executeQuery();
			logger.debug("FindAll with FIELD=" + field_order + " ORDER="
					+ order + " done.");
			return SQLMapper.getComputers(result);
		} catch (SQLException e) {
			logger.error("Error on findAll with FIELD={} ORDER={} !",
					field_order, order);
			throw new DAOException(e);
		} finally {
			connectionFactory.closeConnection(state, result);
		}
	}

	public List<Computer> findAll(String search, int offset, int limit,
			String field_order, String order) {
		Connection connect = connectionFactory.getConnection();
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
			logger.debug("FindAll with SEARCH={} FIELD={} ORDER={} done.",
					search, field_order, order);
			return SQLMapper.getComputers(result);
		} catch (SQLException e) {
			logger.error(
					"Error on findAll(search) with SEARCH= {} FIELD={} ORDER={} !",
					search, field_order, order);
			throw new DAOException(e);
		} finally {
			connectionFactory.closeConnection(state, result);
		}
	}

	/**
	 * Find all computers with company_id equal id
	 * 
	 * @param id
	 * @return
	 */
	public List<Computer> findAllCompany(long company_id) {
		Connection connect = connectionFactory.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			state = connect.prepareStatement(FIND_ALL + " WHERE company_id=?");
			state.setLong(1, company_id);
			result = state.executeQuery();
			return SQLMapper.getComputers(result);
		} catch (SQLException e) {
			logger.error("Error on findAlCompanyl with company_id={} !",
					company_id);
			throw new DAOException(e);
		} finally {
			connectionFactory.closeConnection(state, result);
		}
	}

	public void deleteByCompany(long company_id) throws SQLException {
		Connection connect = connectionFactory.getConnection();
		PreparedStatement state = null;
		state = connect
				.prepareStatement("DELETE FROM computer WHERE company_id=?");
		state.setLong(1, company_id);
		state.executeUpdate();
		if (state != null)
			state.close();
	}
}
