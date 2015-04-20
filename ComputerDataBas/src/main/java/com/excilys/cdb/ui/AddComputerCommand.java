package com.excilys.cdb.ui;

import java.text.ParseException;
import java.time.LocalDateTime;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.mapper.TimeMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerDAOService;

public class AddComputerCommand extends ICommand {

	@Override
	public void fetch() {

		long id = 0;
		LocalDateTime intro = null, dis = null;

		System.out.println("Enter a computer name >");
		String name = Main.sc.next();
		try {
			if (chooseArgs("add introduced date")) {
				intro = TimeMapper.TimestampToLocalDateTime(getDate());
			}
			if (chooseArgs("add discontinued date")) {
				dis = TimeMapper.TimestampToLocalDateTime(getDate());
			}
			if (chooseArgs("add company id")) {
				id = Main.getLong();
			}
			Company company = new Company();
			company.setId(id);
			Computer comp = new Computer(name, intro, dis, company);
			ComputerDAOService.INSTANCE.addComputer(comp);
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
