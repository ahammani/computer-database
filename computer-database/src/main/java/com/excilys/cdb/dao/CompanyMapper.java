package com.excilys.cdb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.model.Company;

public class CompanyMapper implements RowMapper<Company> {

	@Override
	public Company mapRow(ResultSet result, int arg1) throws SQLException {
		String name = result.getString("name");
		long id = result.getLong("id");
		Company comp = new Company(id, name);
		return comp;
	}

}
