package com.excilys.cdb.main;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Computer.ComputerBuilder;
import com.excilys.cdb.service.IClientService;
import com.excilys.cdb.service.IClientService2;

@Component
public class Menu {
	@Autowired
	public IClientService service;

	public void findAllCompany() {
		List<Company> comp = service.findAllCompany();
		for (Company c : comp) {
			if (c != null)
				System.out.println(c.toString());
		}
	}

	public void findAllComputer() {
		List<Computer> comp = service.findAllComputer();
		for (Computer c : comp) {
			if (c != null)
				System.out.println(c.toString());
		}
	}

	public void findComputer(long id) {
		Computer comp = service.findComputer(id);
		System.out.println(comp);
	}

	public void addComputer(Computer c) {
		service.createComputer(c);
		System.out.println("Add computer done");
	}

	public void deleteComputer(long id) {
		service.deleteComputer(id);
		System.out.println("Delete computer done");
	}

	public void updateComputer(Computer c) {
		service.updateComputer(c);
		System.out.println("Update done");
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		Menu menu = context.getBean(Menu.class);
		Company company = new Company(2, "Thinking Machines");
		LocalDate date = LocalDate.now();
		Computer c = new ComputerBuilder("TOTO").introduced(date).id(14)
				.discontinued(date).company(company).build();

		// menu.findAllCompany();
		// menu.findComputer(1);
		// menu.findAllComputer();

		// menu.addComputer(c);
		// menu.deleteComputer(13);
		menu.updateComputer(c);

	}
}
