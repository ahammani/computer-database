package com.excilys.cdb.command;

import com.excilys.cdb.main.Main;

public class ExitCommand extends ICommand {

	@Override
	public void fetch() {
		System.out.println("Good bye !");
		Main.sc.close();
		System.exit(0);
	}

	@Override
	public String toString() {
		return "Exit";
	}

}
