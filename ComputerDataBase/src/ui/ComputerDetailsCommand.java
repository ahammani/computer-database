package ui;

import main.Main;
import data.Computer;

public class ComputerDetailsCommand extends Command {

	@Override
	public void fetch() {
		System.out.print("(id de l'ordinateur)");
		int id = Main.getInt();
		Computer c = Main.computers.find(id);
		System.out.println(c.toString());
	}

	@Override
	public String toString() {
		return "Computer Details";
	}

}
