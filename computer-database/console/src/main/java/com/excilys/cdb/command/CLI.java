package com.excilys.cdb.command;

import com.excilys.cdb.model.Computer;

/**
 * Command Line Interface
 * 
 * @author ahammani
 *
 */
public class CLI implements UI {
	private ICommand[] actions;

	public CLI() {
	}

	public CLI(ICommand[] actions) {
		this.actions = actions;
	}

	@Override
	public void displayMenu() {
		System.out.println();
		for (int i = 0; i < actions.length; i++) {
			System.out.println(i + " - " + actions[i].toString());
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
