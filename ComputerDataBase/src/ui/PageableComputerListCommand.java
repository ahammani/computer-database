package ui;

import main.Main;
import data.Computer;
import page.Page;

public class PageableComputerListCommand extends Command {
	Page<Computer> pages;

	@Override
	public void fetch() {
		pages = new Page<Computer>(Main.computers.getList(), 20) {
			public void display() {
				for (int i = 0; i < getNbfield(); i++) {
					int index = (getCurrentPage() * getNbfield()) + i;
					if (index < getData().size()) {
						Computer c = getData().get(index);
						if (c != null)
							Main.cli.simpleDisplay(c);
					}
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
