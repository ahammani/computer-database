package com.excilys.cdb.ui;

import java.util.List;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyService;

public class CompanyListCommand extends ICommand {

	@Override
	public void fetch() {
		List<Company> comp = Main.companyService.getAll();
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
