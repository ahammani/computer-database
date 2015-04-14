package ui;

import java.text.ParseException;
import java.time.LocalDateTime;

import service.ComputerDAOService;
import main.Main;
import mapper.Mapper;
import model.Company;
import model.Computer;

public class AddComputerCommand extends Command {

	@Override
	public void fetch() {

		Long id = null;
		LocalDateTime intro = null, dis = null;

		System.out.println("Enter a computer name >");
		String name = Main.sc.next();
		try {
			if (chooseArgs("add introduced date")) {
				intro = Mapper.TimestampToLocalDateTime(getDate());
			}
			if (chooseArgs("add discontinued date")) {
				dis = Mapper.TimestampToLocalDateTime(getDate());
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
