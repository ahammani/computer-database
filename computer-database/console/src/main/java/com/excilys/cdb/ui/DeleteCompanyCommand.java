package com.excilys.cdb.ui;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.service.CompanyService;

public class DeleteCompanyCommand extends ICommand {

	@Override
	public void fetch() {
		System.out.println("You have to enter the company's id");
		long id = Main.getLong();
		System.out.println("Let's go !");
		Main.companyService.deleteCompany(id);
		System.out.println("Operation done");
	}

	@Override
	public String toString() {
		return "Delete a company";
	}

}