package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public void delete(int id) {
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
			result.close();
			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return company;
	}

	@Override
	public List<Company> getList() {
		List<Company> l = new ArrayList<>();
		try {
			Statement state = connect.createStatement();
			ResultSet result = state
					.executeQuery("SELECT DISTINCT name FROM computer ORDER BY name ASC");

			while (result.next()) {
				String s = (String) result.getObject(1);
				Company obj = new Company(s);
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
