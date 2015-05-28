package com.excilys.cdb.command;

import java.util.List;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.IClientService;

public class CompanyListCommand extends ICommand {
	private IClientService service;

	public CompanyListCommand(IClientService service) {
		this.service = service;
	}

	public IClientService getService() {
		return service;
	}

	public void setService(IClientService service) {
		this.service = service;
	}

	@Override
	public void fetch() {
		List<Company> comp = service.findAllCompany();
		for (Company c : comp) {
			if (c != null)
				System.out.println(c.toString());
		}

	}

	@Override
	public String toString() {
		return "Companies list";
	}

}
