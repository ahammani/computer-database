package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ComputerDAO extends DAO<Computer> {

	public ComputerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(Computer obj) {
		try {
			String name = obj.getName();
			Timestamp introduced = obj.getIntro_date();
			Timestamp discontinued = obj.getDis_date();
			int id_company = obj.getCompany_id();
			PreparedStatement state = connect
					.prepareStatement("INSERT INTO computer(name,introduced,discontinued,company_id) VALUES (?,?,?,?)");
			state.setString(1, name);
			state.setTimestamp(2, introduced);
			state.setTimestamp(3, discontinued);
			state.setLong(4, id_company);
			state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		try {
			PreparedStatement state = connect
					.prepareStatement("DELETE FROM computer WHERE id=?");
			state.setLong(1, id);
			state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(Computer obj) {
		try {
			String name = obj.getName();
			Timestamp introduced = obj.getIntro_date();
			Timestamp discontinued = obj.getDis_date();
			int id_company = obj.getCompany_id();
			int id = obj.getId();
			PreparedStatement state = connect
					.prepareStatement("UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?");
			state.setString(1, name);
			state.setTimestamp(2, introduced);
			state.setTimestamp(3, discontinued);
			state.setInt(4, id_company);
			state.setInt(5, id);
			state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
						result.getTimestamp("discontinued"),
						result.getInt("company_id"), result.getInt("id"));
			}
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
					.executeQuery("SELECT * FROM computer ORDER BY id ASC");
			while (result.next()) {
				long id = (long) result.getObject("id");
				String name = (String) result.getObject("name");
				Timestamp intro = result.getTimestamp("introduced");
				Timestamp dis = result.getTimestamp("discontinued");
				int cid = result.getInt("company_id");
				Computer obj = new Computer(name, intro, dis, (int) cid,
						(int) id);
				l.add(obj);
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
