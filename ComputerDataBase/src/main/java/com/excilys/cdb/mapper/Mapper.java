package main.java.com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;

public class Mapper {

	public static List<Computer> getComputers(ResultSet result)
			throws SQLException {

		List<Computer> l = new ArrayList<>();
		Computer computer = null;
		while ((computer = Mapper.ResultSetToComputer(result)) != null) {
			l.add(computer);
		}
		return l;

	}

	public static Computer ResultSetToComputer(ResultSet result)
			throws SQLException {

		if (result.next()) {
			String name = result.getString("c_name");
			LocalDateTime intro = Mapper.TimestampToLocalDateTime(result
					.getTimestamp("introduced"));
			LocalDateTime dis = Mapper.TimestampToLocalDateTime(result
					.getTimestamp("discontinued"));
			long c_id = result.getLong("c_id");

			Company comp = new Company(result.getLong("company_id"),
					result.getString("name"));
			comp.setId(result.getLong("company_id"));
			return new Computer(name, intro, dis, comp, c_id);
		}
		return null;
	}

	public static Timestamp LocalDateTimeToTimestamp(LocalDateTime ldt) {
		if (ldt != null)
			return Timestamp.valueOf(ldt);
		else
			return null;
	}

	public static LocalDateTime TimestampToLocalDateTime(Timestamp ts) {
		if (ts != null)
			return ts.toLocalDateTime();
		else
			return null;
	}

}
