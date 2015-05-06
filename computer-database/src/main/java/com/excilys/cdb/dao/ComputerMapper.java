package com.excilys.cdb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Computer.ComputerBuilder;

public class ComputerMapper implements RowMapper<Computer> {

	@Override
	public Computer mapRow(ResultSet result, int arg1) throws SQLException {
		String name = result.getString("c_name");
		LocalDate intro = SQLMapper.timestampToLocaldate(result
				.getTimestamp("introduced"));
		LocalDate dis = SQLMapper.timestampToLocaldate(result
				.getTimestamp("discontinued"));
		long c_id = result.getLong("c_id");

		Company comp = new Company(result.getLong("company_id"),
				result.getString("name"));
		comp.setId(result.getLong("company_id"));
		return new ComputerBuilder(name).id(c_id).introduced(intro)
				.discontinued(dis).company(comp).build();
	}

}
