package main.java.com.excilys.cdb.ui;

import main.java.com.excilys.cdb.main.Main;
import main.java.com.excilys.cdb.service.ComputerDAOService;

public class DeleteComputerCommand extends ICommand {

	@Override
	public void fetch() {
		System.out.println("You have to enter the computer's id");
		long id = Main.getLong();
		ComputerDAOService.INSTANCE.deleteComputer(id);
		System.out.println("Operation done");
	}

	@Override
	public String toString() {
		return "Delete a computer";
	}

}
