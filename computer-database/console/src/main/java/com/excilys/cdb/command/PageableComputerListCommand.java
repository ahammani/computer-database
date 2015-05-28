package com.excilys.cdb.command;

import java.util.List;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.Page;
import com.excilys.cdb.service.IClientService;

public class PageableComputerListCommand extends ICommand {
	List<Computer> l;
	private int offset = 0;
	private int limit = 10;
	IClientService service;

	public PageableComputerListCommand(IClientService service, int limit) {
		this.service = service;
		Page p = new Page(1, limit);
		this.limit = limit;
		l = service.findAllComputer(p);
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
		l = service.findAllComputer(p);
		for (int i = 0; i < l.size(); i++) {
			Computer c = l.get(i);
			if (c != null)
				System.out.println("ID : " + c.getId() + " " + "NAME : "
						+ c.getName());
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
