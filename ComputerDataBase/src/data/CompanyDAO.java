package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ui.CLI;

public class CompanyDAO extends DAO<Company> {

	public CompanyDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(Company obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Company obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Company obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Company find(int id) {
		Company company = new Company();
		try {
			Statement state = connect.createStatement();
			ResultSet result = state
					.executeQuery("SELECT * FROM company WHERE company.id="
							+ id);
			if (result.next()) {
				company = new Company(result.getString("name"));
			}
			CLI.display(result);
			result.close();
			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return company;
	}

	// A changer
	@Override
	public List<Company> getList() {
		List<Company> l = new ArrayList<>();
		try {
			Statement state = connect.createStatement();
			ResultSet result = state
					.executeQuery("SELECT DISTINCT name FROM computer ORDER BY name ASC");

			ResultSetMetaData resmet = result.getMetaData();
			while (result.next()) {
				for (int i = 1; i <= resmet.getColumnCount(); i++) {
					if (result.getObject(i) != null) {
						Company obj = (Company) result.getObject(i);
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
