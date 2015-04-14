package ui;

import java.util.List;

import service.ComputerDAOService;
import main.Main;
import model.Computer;

public class ComputerListCommand extends Command {

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
