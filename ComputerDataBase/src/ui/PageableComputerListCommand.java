package ui;

import java.util.List;

import main.Main;
import model.Computer;
import page.Page;
import service.ComputerDAOService;

public class PageableComputerListCommand extends Command {
	Page<Computer> pages;

	@Override
	public void fetch() {
		List<Computer> l = ComputerDAOService.INSTANCE.getAll();
		pages = new Page<Computer>(l, 20) {
			public void display() {
				for (int i = 0; i < getData().size(); i++) {
					Computer c = getData().get(i);
					if (c != null)
						Main.cli.simpleDisplay(c);
				}
				turnPages();
			}
		};
		pages.display();

	}

	@Override
	public String toString() {
		return "Computers list (pages)";
	}

}
