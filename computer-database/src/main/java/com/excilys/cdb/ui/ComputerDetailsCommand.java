package com.excilys.cdb.ui;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;

public class ComputerDetailsCommand extends ICommand {

	@Override
	public void fetch() {
		System.out.print("(computer's id)");
		long id = Main.getLong();
		Computer c = ComputerService.INSTANCE.getComputer(id);
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
