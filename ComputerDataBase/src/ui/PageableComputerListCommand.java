package ui;

import main.Main;
import data.Computer;
import page.Page;

public class PageableComputerListCommand extends Command {
	Page<Computer> pages;

	@Override
	public void fetch() {
		pages = new Page<>(Main.computers.getList(), 20);
		pages.display();

	}

	@Override
	public String toString() {
		return "Computers list (pages)";
	}

}
