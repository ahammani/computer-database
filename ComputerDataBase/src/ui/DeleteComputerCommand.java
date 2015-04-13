package ui;

import main.Main;

public class DeleteComputerCommand extends Command {

	@Override
	public void fetch() {
		System.out.println("You have to enter the computer's id");
		int id = Main.getInt();
		Main.computers.delete(id);
		System.out.println("Operation done");
	}

	@Override
	public String toString() {
		return "Delete a computer";
	}

}
