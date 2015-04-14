package ui;

import java.util.List;

import service.CompanyDAOService;
import model.Company;

public class CompanyListCommand extends Command {

	@Override
	public void fetch() {
		List<Company> comp = CompanyDAOService.INSTANCE.getAll();
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
