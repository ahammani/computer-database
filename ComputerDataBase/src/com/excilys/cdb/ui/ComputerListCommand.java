package com.excilys.cdb.ui;

import java.util.List;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerDAOService;

public class ComputerListCommand extends ICommand {

	@Override
	public void fetch() {
		List<Computer> comp = ComputerDAOService.INSTANCE.getAll();

		for (Computer c : comp) {
			if (c != null)
				Main.cli.simpleDisplay(c);
		}
	}

	@Override
	public String toString() {
		return "Computers list";
	}

}
