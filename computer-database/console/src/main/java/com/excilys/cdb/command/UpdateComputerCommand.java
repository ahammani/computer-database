package com.excilys.cdb.command;

import java.text.ParseException;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.mapper.DTOMapper;
import com.excilys.cdb.model.Computer;

public class UpdateComputerCommand extends ICommand {

	DTOMapper mapper = new DTOMapper();

	@Override
	public void fetch() {
		System.out.print("(You have to enter a computer's id)");
		long id = Main.getLong();
		Computer comp = null;// Main.service.findComputer(id);
		if (comp == null)
			return;
		try {
			if (chooseArgs("modify introduced date")) {
				comp.setIntroduced(getLocalDate());
			}
			if (chooseArgs("modify discontinued date")) {
				comp.setDiscontinued(getLocalDate());
			}
			if (chooseArgs("modify company id")) {
				comp.getCompany().setId(Main.getLong());
			}
			// Main.service.updateComputer(mapper.toDTO(comp));
			System.out.println("Modification done");
			System.out.println();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return "Update computer's fields";
	}

}
