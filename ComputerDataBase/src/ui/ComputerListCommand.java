package ui;

import java.util.List;

import main.Main;
import data.Computer;

public class ComputerListCommand extends Command {

	@Override
	public void fetch() {
		List<Computer> comp = Main.computers.getList();
		for (Computer c : comp) {
			System.out.println(c.toString());
		}
	}

	@Override
	public String toString() {
		return "Computers list";
	}

}
