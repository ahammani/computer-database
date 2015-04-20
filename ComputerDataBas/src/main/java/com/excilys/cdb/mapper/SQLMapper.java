package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class SQLMapper {
	public static List<Computer> getComputers(ResultSet result)
			throws SQLException {

		List<Computer> l = new ArrayList<>();
		Computer computer = null;
		while ((computer = SQLMapper.ResultSetToComputer(result)) != null) {
			l.add(computer);
		}
		return l;

	}

	public static Computer ResultSetToComputer(ResultSet result)
			throws SQLException {

		if (result.next()) {
			String name = result.getString("c_name");
			LocalDateTime intro = TimeMapper.TimestampToLocalDateTime(result
					.getTimestamp("introduced"));
			LocalDateTime dis = TimeMapper.TimestampToLocalDateTime(result
					.getTimestamp("discontinued"));
			long c_id = result.getLong("c_id");

			Company comp = new Company(result.getLong("company_id"),
					result.getString("name"));
			comp.setId(result.getLong("company_id"));
			return new Computer(name, intro, dis, comp, c_id);
		}
		return null;
	}

	public static Company ResultSetToCompany(ResultSet result)
			throws SQLException {
		if (result.next()) {
			String name = result.getString("name");
			long id = result.getLong("id");

			Company comp = new Company(id, name);
			return comp;
		}
		return null;
	}
}
