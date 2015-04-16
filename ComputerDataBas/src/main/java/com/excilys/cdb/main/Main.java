package com.excilys.cdb.main;

import java.util.Scanner;

import com.excilys.cdb.ui.*;

public class Main {
	public static CLI cli = new CLI();
	public static Scanner sc = new Scanner(System.in);

	public static final ICommand[] actions = { new CompanyListCommand(),
			new ComputerListCommand(), new PageableComputerListCommand(10),
			new ComputerDetailsCommand(), new AddComputerCommand(),
			new UpdateComputerCommand(), new DeleteComputerCommand(),
			new ExitCommand() };

	public static void wrongEntry() {
		System.out.println("Wrong entry !");
	}

	public static boolean isInt(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) < 48 || s.charAt(i) > 57) {
				return false;
			}
		}
		return true;
	}

	public static long getLong() {
		System.out.print("Enter an number > ");
		String s = sc.next();
		while (!isInt(s)) {
			wrongEntry();
			System.out.println("Only numbers 0-9");
			System.out.print("Enter an number > ");
			s = sc.next();
		}
		long i = Long.parseLong(s);
		if (i < 0) {
			wrongEntry();
			return -1;
		}
		return i;
	}

	public static void main(String[] args) {
		while (true) {
			cli.displayMenu();
			int i = (int) getLong();
			if (i >= actions.length) {
				wrongEntry();
			} else
				actions[i].fetch();
		}
	}
}