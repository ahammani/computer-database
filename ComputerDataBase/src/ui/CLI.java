package ui;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.Company;
import data.Computer;
import main.Main;

public class CLI implements UI {
	private List<String> features;

	public CLI() {
		features = new ArrayList<>();
	}

	public CLI(List<String> l) {
		features = new ArrayList<>();
		features.addAll(l);
	}

	@Override
	public void displayMenu() {
		for (int i = 0; i < Main.actions.length; i++)
			System.out.println(i + " - " + Main.actions[i].toString());
	}

	@Override
	public void start() {
		System.out.println("Bonjour !");
		displayMenu();

	}

	public void display(Computer c) {
		System.out.println(c.toString());
	}

	public void display(Company c) {
		System.out.println(c.toString());
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
