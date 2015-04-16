package main.java.com.excilys.cdb.ui;

import java.util.ArrayList;
import java.util.List;

import main.java.com.excilys.cdb.main.Main;
import main.java.com.excilys.cdb.model.Computer;

/**
 * Command Line Interface
 * 
 * @author ahammani
 *
 */
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
		System.out.println();
		for (int i = 0; i < Main.actions.length; i++) {
			System.out.println(i + " - " + Main.actions[i].toString());
		}
		System.out.println();
	}

	public void simpleDisplay(Computer c) {
		System.out.println("ID : " + c.getId() + " " + "NAME : " + c.getName());
	}

	@Override
	public void start() {
		System.out.println("Bonjour !");
		displayMenu();

	}

}
