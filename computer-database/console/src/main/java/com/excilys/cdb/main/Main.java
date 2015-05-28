package com.excilys.cdb.main;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.command.AddComputerCommand;
import com.excilys.cdb.command.CLI;
import com.excilys.cdb.command.CompanyListCommand;
import com.excilys.cdb.command.ComputerDetailsCommand;
import com.excilys.cdb.command.ComputerListCommand;
import com.excilys.cdb.command.DeleteCompanyCommand;
import com.excilys.cdb.command.DeleteComputerCommand;
import com.excilys.cdb.command.ExitCommand;
import com.excilys.cdb.command.ICommand;
import com.excilys.cdb.command.PageableComputerListCommand;
import com.excilys.cdb.command.UpdateComputerCommand;
import com.excilys.cdb.mapper.DTOMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.IClientService;
import com.excilys.cdb.service.IClientService2;
import com.excilys.cdb.utils.Utils;

@Transactional
@Component
public class Main {
	public static CLI cli = new CLI();
	public static Scanner sc = new Scanner(System.in);
	@Autowired
	public IClientService trueService;
	public static IClientService2 service;

	// public static ClassPathXmlApplicationContext context;
	// static {
	// context = new ClassPathXmlApplicationContext(
	// "classpath:applicationContext.xml");
	// service = context.getBean(IClientService2.class);
	// }
	public static final ICommand[] actions = { new CompanyListCommand(),
			new ComputerListCommand(), new PageableComputerListCommand(10),
			new ComputerDetailsCommand(), new AddComputerCommand(),
			new UpdateComputerCommand(), new DeleteComputerCommand(),
			new DeleteCompanyCommand(), new ExitCommand() };

	public static void wrongEntry() {
		System.out.println("Wrong entry !");
	}

	public static long getLong() {
		System.out.print("Enter an number > ");
		String s = sc.next();
		while (!Utils.isNumber(s)) {
			wrongEntry();
			System.out.println("Only numbers 0-9");
			System.out.print("Enter an number > ");
			s = sc.next();
		}
		long i = Long.parseLong(s);
		if (i < 0) {
			wrongEntry();
			return -1;
		}
		return i;
	}

	private void loop() {
		while (true) {
			cli.displayMenu();
			int i = (int) getLong();
			if (i >= actions.length) {
				wrongEntry();
			} else
				actions[i].fetch();
		}
	}

	public void findAllCompany() {
		List<Company> comp = Main.service.findAllCompany();
		for (Company c : comp) {
			if (c != null)
				System.out.println(c.toString());
		}

	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		Main main = context.getBean(Main.class);
		main.findAllCompany();

	}
}
