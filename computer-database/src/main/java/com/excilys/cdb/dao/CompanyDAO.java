package com.excilys.cdb.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Company;

@Repository
public class CompanyDAO implements IDAO<Company> {
	private final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Company find(long id) {
		return this.jdbcTemplate.queryForObject(
				"SELECT * FROM company WHERE id=?", new CompanyMapper(), id);
	}

	@Override
	public List<Company> findAll() {
		return this.jdbcTemplate.query(
				"SELECT DISTINCT * FROM company ORDER BY name ASC",
				new CompanyMapper());
	}

	@Override
	public void delete(long company_id) {
		this.jdbcTemplate.update("DELETE FROM company WHERE id=?", company_id);
	}

}
