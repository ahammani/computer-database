package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ui.CLI;

public class ComputerDAO extends DAO<Computer> {

	public ComputerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(Computer obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Computer obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Computer obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Computer find(int id) {
		Computer computer = new Computer();
		try {
			Statement state = connect.createStatement();
			ResultSet result = state
					.executeQuery("SELECT * FROM computer WHERE computer.id="
							+ id);
			if (result.next()) {
				computer = new Computer(result.getString("name"),
						result.getTimestamp("introduced"),
						result.getTimestamp("discontinued"));
			}
			CLI.display(result);
			result.close();
			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computer;
	}

	@Override
	public List<Computer> getList() {
		List<Computer> l = new ArrayList<>();
		try {
			Statement state = connect.createStatement();
			ResultSet result = state
					.executeQuery("SELECT id,name FROM computer ORDER BY id ASC");

			ResultSetMetaData resmet = result.getMetaData();
			while (result.next()) {
				for (int i = 1; i <= resmet.getColumnCount(); i++) {
					if (result.getObject(i) != null) {
						Computer obj = (Computer) result.getObject(i);
						l.add(obj);
					}
				}
			}
			result.close();
			state.close();
			return l;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;

	}

}
