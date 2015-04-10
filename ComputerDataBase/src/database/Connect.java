package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	private String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private String usr = "admincdb";
	private String pwd = "qwerty1234";
	private static Connection connect;

	private Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, usr, pwd);
		} catch (SQLException e) {
			System.out.println("Impossible de se connecter à la base !");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getInstance() {
		if (connect == null) {
			new Connect();
		}
		return connect;
	}
}
