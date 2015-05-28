package com.excilys.cdb.command;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.IClientService;

public class ComputerDetailsCommand extends ICommand {
	IClientService service;

	public ComputerDetailsCommand(IClientService service) {
		this.service = service;
	}

	@Override
	public void fetch() {
		System.out.print("(computer's id)");
		long id = Main.getLong();
		Computer c = service.findComputer(id);
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
