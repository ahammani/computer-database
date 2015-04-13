package main;

import java.util.Scanner;

import ui.*;
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

	public static final Command[] actions = { new CompanyListCommand(),
			new ComputerListCommand(), new ComputerDetailsCommand(),
			new AddComputerCommand(), new UpdateComputerCommand(),
			new DeleteComputerCommand(), new ExitCommand() };

	public static void wrongEntry() {
		System.out.println("Wrong entry !");
	}

	public static int getInt() {
		System.out.print("Enter an number > ");

		String s = sc.next();
		int i = Integer.parseInt(s);
		if (i < 0) {
			wrongEntry();
			return -1;
		}

		return i;
	}

	public static void main(String[] args) {
		while (true) {
			cli.displayMenu();
			int i = getInt();
			if (i >= actions.length) {
				wrongEntry();
			} else
				actions[i].fetch();
		}
	}
}
