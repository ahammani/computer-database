package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Company;

public enum CompanyDAO implements ICompanyDAO {
	INSTANCE;
	@Override
	public List<Company> findAll() {
		Connection connect = FactoryConnection.INSTANCE.openConnection();
		PreparedStatement state = null;
		List<Company> l = new ArrayList<>();
		try {
			state = connect
					.prepareStatement("SELECT DISTINCT name FROM computer ORDER BY name ASC");
			ResultSet result = state.executeQuery();

			while (result.next()) {
				String s = (String) result.getObject(1);
				Company obj = new Company(s);
				l.add(obj);
			}
			return l;
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			FactoryConnection.INSTANCE.closeConnection(connect, state);
		}

	}

}
