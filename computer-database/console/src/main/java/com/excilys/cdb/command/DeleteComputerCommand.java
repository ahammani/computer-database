package com.excilys.cdb.command;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.service.IClientService;

public class DeleteComputerCommand extends ICommand {
	IClientService service;

	public DeleteComputerCommand(IClientService service) {
		this.service = service;
	}

	@Override
	public void fetch() {
		System.out.println("You have to enter the computer's id");
		long id = Main.getLong();
		service.deleteComputer(id);
		System.out.println("Operation done");
	}

	@Override
	public String toString() {
		return "Delete a computer";
	}

}
