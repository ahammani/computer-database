package com.excilys.cdb.command;

import java.text.ParseException;
import java.time.LocalDate;

import com.excilys.cdb.dao.SQLMapper;
import com.excilys.cdb.main.Main;
import com.excilys.cdb.mapper.DTOMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Computer.ComputerBuilder;
import com.excilys.cdb.service.IClientService;

public class AddComputerCommand extends ICommand {
	DTOMapper mapper = new DTOMapper();
	IClientService service;

	public AddComputerCommand(IClientService service) {
		this.service = service;
	}

	@Override
	public void fetch() {

		long id = 0;
		LocalDate intro = null, dis = null;

		System.out.println("Enter a computer name >");
		String name = Main.sc.next();
		try {
			if (chooseArgs("add introduced date")) {
				intro = SQLMapper.timestampToLocaldate(getDate());
			}
			if (chooseArgs("add discontinued date")) {
				dis = SQLMapper.timestampToLocaldate(getDate());
			}
			if (chooseArgs("add company id")) {
				id = Main.getLong();
			}
			Company company = new Company();
			company.setId(id);
			Computer comp = new ComputerBuilder(name).introduced(intro)
					.discontinued(dis).company(company).build();
			service.createComputer(comp);
			System.out.println("Creation done");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Add a new computer";
	}

}
