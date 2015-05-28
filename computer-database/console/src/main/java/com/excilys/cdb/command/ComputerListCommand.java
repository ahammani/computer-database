package com.excilys.cdb.command;

import java.util.List;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.mapper.DTOMapper;
import com.excilys.cdb.model.Computer;

public class ComputerListCommand extends ICommand {

	DTOMapper mapper = new DTOMapper();

	@Override
	public void fetch() {
		List<Computer> comp = mapper.toComputerList(Main.service.findAllComputer());

		for (Computer c : comp) {
			if (c != null)
				Main.cli.simpleDisplay(c);
		}
	}

	@Override
	public String toString() {
		return "Computers list";
	}

}
