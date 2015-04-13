package ui;

import main.Main;

public class ExitCommand extends Command {

	@Override
	public void fetch() {
		System.out.println("Good bye !");
		Main.sc.close();
		System.exit(0);
	}

	@Override
	public String toString() {
		return "Exit";
	}

}
