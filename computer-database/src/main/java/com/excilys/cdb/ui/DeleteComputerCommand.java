package com.excilys.cdb.ui;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.service.ComputerService;

public class DeleteComputerCommand extends ICommand {

	@Override
	public void fetch() {
		System.out.println("You have to enter the computer's id");
		long id = Main.getLong();
		ComputerService.INSTANCE.deleteComputer(id);
		System.out.println("Operation done");
	}

	@Override
	public String toString() {
		return "Delete a computer";
	}

}
