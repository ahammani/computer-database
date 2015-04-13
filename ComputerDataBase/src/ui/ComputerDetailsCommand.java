package ui;

import main.Main;
import data.Computer;

public class ComputerDetailsCommand extends Command {

	@Override
	public void fetch() {
		System.out.print("(computer's id)");
		int id = Main.getInt();
		Computer c = Main.computers.find(id);
		if (c != null && c.getId() > 0)
			System.out.println(c.toString());
		else
			System.out.println("Computer doesn't exist");
	}

	@Override
	public String toString() {
		return "Computer Details";
	}

}
