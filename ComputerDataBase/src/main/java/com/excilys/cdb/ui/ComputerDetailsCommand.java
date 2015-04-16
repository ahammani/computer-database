package main.java.com.excilys.cdb.ui;

import main.java.com.excilys.cdb.main.Main;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.service.ComputerDAOService;

public class ComputerDetailsCommand extends ICommand {

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
