package com.excilys.cdb.command;

import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.IClientService;

public class ComputerListCommand extends ICommand {

	IClientService service;

	public ComputerListCommand(IClientService service) {
		this.service = service;
	}

	@Override
	public void fetch() {
		List<Computer> comp = service.findAllComputer();
		for (Computer c : comp) {
			if (c != null) {
				System.out.println("ID : " + c.getId() + " " + "NAME : "
						+ c.getName());
			}
		}
	}

	@Override
	public String toString() {
		return "Computers list";
	}

}
