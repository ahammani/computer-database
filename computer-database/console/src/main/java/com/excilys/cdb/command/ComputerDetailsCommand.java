package com.excilys.cdb.command;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Computer;

public class ComputerDetailsCommand extends ICommand {

	@Override
	public void fetch() {
		System.out.print("(computer's id)");
		long id = Main.getLong();
		Computer c = null; // Main.service.findComputer(id);
		if (c != null && c.getId() > 0)
			System.out.println(c.toString());
		else
			System.out.println("Computer doesn't exist");
	}

	@Override
	public String toString() {
		return "Computer Details";
	}

}
