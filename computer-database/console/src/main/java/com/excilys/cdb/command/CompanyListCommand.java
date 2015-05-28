package com.excilys.cdb.command;

import java.util.List;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Company;

public class CompanyListCommand extends ICommand {

	@Override
	public void fetch() {
		List<Company> comp = Main.service.findAllCompany();
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
