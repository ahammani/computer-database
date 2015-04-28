package com.excilys.cdb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

/**
 * 
 * @author ahammani Utils for converting sql stuff
 */
public class SQLMapper {

	public static Timestamp localdateToTimestamp(LocalDate ldt) {
		if (ldt != null)
			return Timestamp.valueOf(ldt.atStartOfDay());
		else
			return null;
	}

	public static LocalDate timestampToLocaldate(Timestamp ts) {
		if (ts != null)
			return ts.toLocalDateTime().toLocalDate();
		else
			return null;
	}

	public static List<Computer> getComputers(ResultSet result)
			throws SQLException {

		List<Computer> l = new ArrayList<>();
		Computer computer = null;
		while ((computer = SQLMapper.resultSetToComputer(result)) != null) {
			l.add(computer);
		}
		return l;

	}

	public static Computer resultSetToComputer(ResultSet result)
			throws SQLException {

		if (result.next()) {
			String name = result.getString("c_name");
			LocalDate intro = SQLMapper.timestampToLocaldate(result
					.getTimestamp("introduced"));
			LocalDate dis = SQLMapper.timestampToLocaldate(result
					.getTimestamp("discontinued"));
			long c_id = result.getLong("c_id");

			Company comp = new Company(result.getLong("company_id"),
					result.getString("name"));
			comp.setId(result.getLong("company_id"));
			return new Computer(name, intro, dis, comp, c_id);
		}
		return null;
	}

	public static Company resultSetToCompany(ResultSet result)
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
