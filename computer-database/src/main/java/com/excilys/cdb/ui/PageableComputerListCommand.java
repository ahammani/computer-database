package com.excilys.cdb.ui;

import java.util.List;

import page.Page;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerDAOService;

public class PageableComputerListCommand extends ICommand {
	List<Computer> l;
	private int offset = 0;
	private int limit = 10;

	public PageableComputerListCommand(int limit) {
		Page p = new Page(1, limit);
		l = ComputerDAOService.INSTANCE.getAll(p, "", "");
		this.limit = limit;
	}

	private void next() {
		if (l.size() == limit) {
			offset += limit;
		} else {
			System.out.println("No more pages");
		}
	}

	private void previous() {
		if (offset > 0) {
			offset -= limit;
		} else {
			System.out.println("It's the first page");
		}
	}

	private void turnPages() {
		System.out.println("< p 	e (exit)	 n >");
		System.out.print(" > ");
		String ans = Main.sc.next().substring(0, 1).toLowerCase();
		switch (ans) {
		case "p":
			previous();
			display();
			break;
		case "n":
			next();
			display();
			break;
		case "e":
			break;
		default:
			break;
		}
	}

	private void display() {
		Page p = new Page(1, limit);
		l = ComputerDAOService.INSTANCE.getAll(p, "", "");
		for (int i = 0; i < l.size(); i++) {
			Computer c = l.get(i);
			if (c != null)
				Main.cli.simpleDisplay(c);
		}
		turnPages();
	}

	@Override
	public void fetch() {
		display();
	}

	@Override
	public String toString() {
		return "Computers list (pages)";
	}

}
