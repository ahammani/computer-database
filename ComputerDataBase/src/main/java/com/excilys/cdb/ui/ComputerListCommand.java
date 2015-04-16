package main.java.com.excilys.cdb.ui;

import java.util.List;

import main.java.com.excilys.cdb.main.Main;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.service.ComputerDAOService;

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
