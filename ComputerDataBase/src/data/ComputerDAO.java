package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

	// A changer
	@Override
	public void getList() {
		try {
			Statement state = connect.createStatement();
			ResultSet result = state
					.executeQuery("SELECT DISTINCT name FROM computer ORDER BY name ASC");
			CLI.display(result);
			result.close();
			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
