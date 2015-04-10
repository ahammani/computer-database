package main;

import java.util.Scanner;

import ui.CLI;
import data.Company;
import data.CompanyDAO;
import data.Computer;
import data.ComputerDAO;
import data.DAO;
import database.*;

public class Main {
	public static CLI cli = new CLI();
	public static DAO<Computer> computers = new ComputerDAO(
			Connect.getInstance());
	public static DAO<Company> companies = new CompanyDAO(Connect.getInstance());
	public static Scanner sc = new Scanner(System.in);

	public static final String[] actions = { "Computers list",
			"Companies list", "Computer details", "Add a new computer",
			"Update a computer", "Delete a computer" };

	public static int getAction() {
		System.out.print("Entrez un entier > ");

		String s = sc.next();
		int i = Integer.parseInt(s);
		if (i < 0 && i >= actions.length) {
			System.out.println("Mauvaise entree !");
			return -1;
		}

		return i;
	}

	public static void main(String[] args) {
		cli.start();
		boolean loop = true;
		while (loop) {
			cli.displayMenu();
			switch (getAction()) {
			case -1:
				break;
			case 0:
				computers.getList();
				break;
			case 1:
				companies.getList();
				break;
			case 2:
				System.out.print("(id de l'ordinateur)");
				int id = getAction();
				cli.showComputerDetails(id);
			default:
				loop = false;
			}
		}
		// Computer c = computers.find(5);
		// companies.getList();
		// Company apple = companies.find(2);
		// System.out.println(apple.getName());
	}
}
