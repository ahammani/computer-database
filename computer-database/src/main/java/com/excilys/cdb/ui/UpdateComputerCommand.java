package com.excilys.cdb.ui;

import java.text.ParseException;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerDAOService;

public class UpdateComputerCommand extends ICommand {

	@Override
	public void fetch() {
		System.out.print("(You have to enter a computer's id)");
		long id = Main.getLong();
		Computer comp = ComputerDAOService.INSTANCE.getComputer(id);
		if (comp == null)
			return;
		try {
			if (chooseArgs("modify introduced date")) {
				comp.setIntro_date(getLocalDateTime());
			}
			if (chooseArgs("modify discontinued date")) {
				comp.setDis_date(getLocalDateTime());
			}
			if (chooseArgs("modify company id")) {
				comp.getCompany().setId(Main.getLong());
			}
			ComputerDAOService.INSTANCE.updateComputer(comp);
			System.out.println("Modification done");
			System.out.println();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return "Update computer's fields";
	}

}
