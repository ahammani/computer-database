package ui;

import java.sql.Timestamp;
import java.text.ParseException;
import data.Computer;
import main.Main;

public class AddComputerCommand extends Command {

	@Override
	public void fetch() {

		Integer id = null;
		Timestamp intro = null, dis = null;

		System.out.println("Enter a computer name >");
		String name = Main.sc.next();
		try {
			if (chooseArgs("add introduced date")) {
				intro = getDate();
			}
			if (chooseArgs("add discontinued date")) {
				dis = getDate();
			}
			if (chooseArgs("add company id")) {
				id = Main.getInt();
			}
			Computer comp = new Computer(name, intro, dis, id);
			Main.computers.create(comp);
			System.out.println("Creation done");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Add a new computer";
	}

}
