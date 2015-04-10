package ui;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CLI implements UI {

	@Override
	public void display() {
		System.out.println("What do you want?");
		System.out.println("0 - Computers list");
		System.out.println("1 - Companies list");
		System.out
				.println("2 - Computer details (press '2 ' followed by computer's id)");
		System.out.println("3 - Add a new computer");
		System.out.println("4 - Update a computer");
		System.out.println("5 - Delete a computer (same syntax as 2)");

	}

	@Override
	public void start() {
		System.out.println("Welcome !");
		display();

	}

	public static void display(ResultSet result) {
		try {
			ResultSetMetaData resmet = result.getMetaData();
			while (result.next()) {
				for (int i = 1; i <= resmet.getColumnCount(); i++) {

					String obj = "    ";
					if (result.getObject(i) != null) {
						obj = result.getObject(i).toString();
					}
					System.out.print(resmet.getColumnName(i).toUpperCase()
							+ " : " + obj + "\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
