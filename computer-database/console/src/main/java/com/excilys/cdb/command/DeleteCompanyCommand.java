package com.excilys.cdb.command;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.service.IClientService;

public class DeleteCompanyCommand extends ICommand {
	IClientService service;

	public DeleteCompanyCommand(IClientService service) {
		this.service = service;
	}

	@Override
	public void fetch() {
		System.out.println("You have to enter the company's id");
		long id = Main.getLong();
		System.out.println("Let's go !");
		service.deleteCompany(id);
		System.out.println("Operation done");
	}

	@Override
	public String toString() {
		return "Delete a company";
	}

}
