package ui;

import java.text.ParseException;

import main.Main;
import data.Computer;

public class UpdateComputerCommand extends Command {

	@Override
	public void fetch() {
		System.out.print("(You have to enter a computer's id)");
		int id = Main.getInt();
		Computer comp = Main.computers.find(id);
		System.out.println("IIIIIIIIIIIIIIIIIIII" + comp.getCompany_id());
		try {
			if (chooseArgs("modify introduced date")) {
				comp.setIntro_date(getDate());
			}
			if (chooseArgs("modify discontinued date")) {
				comp.setDis_date(getDate());
			}
			if (chooseArgs("modify company id")) {
				comp.setCompany_id(Main.getInt());
			}
			Main.computers.update(comp);
			System.out.println("Modification done");
			System.out.println();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return "Modify computer's fields";
	}

}
