package ui;

import service.ComputerDAOService;
import main.Main;
import model.Computer;

public class ComputerDetailsCommand extends Command {

	@Override
	public void fetch() {
		System.out.print("(computer's id)");
		long id = Main.getLong();
		Computer c = ComputerDAOService.INSTANCE.getComputer(id);
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
