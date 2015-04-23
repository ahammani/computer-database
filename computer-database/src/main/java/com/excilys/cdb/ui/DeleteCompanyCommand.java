package com.excilys.cdb.ui;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.service.CompanyDAOService;

public class DeleteCompanyCommand extends ICommand {

	@Override
	public void fetch() {
		System.out.println("You have to enter the company's id");
		long id = Main.getLong();
		CompanyDAOService.INSTANCE.deleteCompany(id);
		System.out.println("Operation done");
	}

	@Override
	public String toString() {
		return "Delete a company";
	}

}
