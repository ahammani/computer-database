package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

@Repository
public class ComputerDAO implements IDAO<Computer> {

	private static final String FIND_ALL = "SELECT computer.id as c_id,computer.name as c_name,introduced,discontinued,company_id,company.name FROM computer LEFT OUTER JOIN company  on computer.company_id=company.id";
	private static final String FIND_ALL_COMPANY = FIND_ALL
			+ " WHERE company_id=?";
	private static final String COUNT = "SELECT COUNT(*) FROM computer";
	private static final String COUNT_SEARCH = "SELECT COUNT(*) FROM computer LEFT OUTER JOIN company on computer.company_id=company.id"
			+ " WHERE computer.name LIKE :search OR company.name LIKE :search";
	private static final String DELETE = "DELETE FROM computer WHERE id=?";
	private static final String FIND = "SELECT computer.id as c_id,computer.name as c_name,introduced,discontinued,company_id,company.name FROM computer LEFT OUTER JOIN company  on computer.company_id=company.id WHERE computer.id=?";
	private static final String UPDATE = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
	private static final String INSERT = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
	private static final String FIND_ALL_ORDER = FIND_ALL
			+ " ORDER BY %s %s LIMIT ? OFFSET ? ";
	private static final String FIND_ALL_SEARCH = FIND_ALL
			+ " WHERE computer.name LIKE :search OR company.name LIKE :search ORDER BY %s %s LIMIT :limit  OFFSET :offset ";

	private final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int count() {
		return this.jdbcTemplate.queryForObject(COUNT, Integer.class);
	}

	// search is checked in Page
	public int count(String search) {
		SqlParameterSource namedParameters = new MapSqlParameterSource(
				"search", "%" + search + "%");
		return this.namedParameterJdbcTemplate.queryForObject(COUNT_SEARCH,
				namedParameters, Integer.class);
	}

	@Override
	public void create(Computer obj) {
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
		KeyHolder keyHolder = new GeneratedKeyHolder();

		int id = this.jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, name);
				ps.setTimestamp(2, introduced);
				ps.setTimestamp(3, discontinued);
				if (id_company == 0) {
					ps.setNull(4, Types.NULL);
				} else {
					ps.setLong(4, id_company);
				}
				return ps;
			}
		}, keyHolder);
		obj.setId(id);
	}

	@Override
	public void delete(long id) {
		this.jdbcTemplate.update(DELETE, id);
	}

	@Override
	public void update(Computer obj) {
		String name = obj.getName();
		Timestamp introduced = SQLMapper.localdateToTimestamp(obj
				.getIntroduced());
		Timestamp discontinued = SQLMapper.localdateToTimestamp(obj
				.getDiscontinued());
		Company company = obj.getCompany();
		long id_company;
		long id = obj.getId();
		if (company != null)
			id_company = company.getId();
		else
			id_company = 0;

		this.jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(UPDATE);
				ps.setString(1, name);
				ps.setTimestamp(2, introduced);
				ps.setTimestamp(3, discontinued);
				if (id_company == 0) {
					ps.setNull(4, Types.NULL);
				} else {
					ps.setLong(4, id_company);
				}
				ps.setLong(5, id);
				return ps;
			}
		});
	}

	@Override
	public Computer find(long id) {
		return this.jdbcTemplate.queryForObject(FIND, new ComputerMapper(), id);
	}

	@Override
	public List<Computer> findAll() {
		return this.jdbcTemplate.query(FIND_ALL, new ComputerMapper());
	}

	// field_order and order are checked in Page
	public List<Computer> findAll(int offset, int limit, String field_order,
			String order) {
		String req = String.format(FIND_ALL_ORDER, field_order, order);
		List<Computer> computers = this.jdbcTemplate.query(req, new Object[] {
				limit, offset }, new ComputerMapper());
		return computers;
	}

	public List<Computer> findAll(String search, int offset, int limit,
			String field_order, String order) {

		String req = String.format(FIND_ALL_SEARCH, field_order, order);
		MapSqlParameterSource namedParameters = new MapSqlParameterSource(
				"search", "%" + search + "%");
		namedParameters.addValue("limit", limit);
		namedParameters.addValue("offset", offset);
		List<Computer> computers = this.namedParameterJdbcTemplate.query(req,
				namedParameters, new ComputerMapper());
		return computers;

	}

	/**
	 * Find all computers with company_id equal id
	 * 
	 * @param id
	 * @return
	 */
	public List<Computer> findAllCompany(long company_id) {
		return this.jdbcTemplate.query(FIND_ALL_COMPANY, new ComputerMapper(),
				company_id);
	}

	public void deleteByCompany(long company_id) {
		this.jdbcTemplate.update(DELETE, company_id);
	}
}
