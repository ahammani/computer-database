package main;

import ui.CLI;
import data.Computer;
import data.ComputerDAO;
import data.DAO;
import database.*;

public class Main {

	public static void main(String[] args) {
		CLI cli = new CLI();
		cli.start();
		DAO<Computer> computers = new ComputerDAO(Connect.getInstance());
		Computer c = computers.find(5);
		System.out.println(c.getIntro_date());
	}

}
