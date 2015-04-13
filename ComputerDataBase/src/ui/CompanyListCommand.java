package ui;

import java.util.List;

import main.Main;
import data.Company;

public class CompanyListCommand extends Command {

	@Override
	public void fetch() {
		List<Company> comp = Main.companies.getList();
		for (Company c : comp) {
			System.out.println(c.toString());
		}

	}

	@Override
	public String toString() {
		return "Companies list";
	}

}
