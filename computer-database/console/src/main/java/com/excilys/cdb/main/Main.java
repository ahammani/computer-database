package com.excilys.cdb.main;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.command.AddComputerCommand;
import com.excilys.cdb.command.CompanyListCommand;
import com.excilys.cdb.command.ComputerDetailsCommand;
import com.excilys.cdb.command.ComputerListCommand;
import com.excilys.cdb.command.DeleteCompanyCommand;
import com.excilys.cdb.command.DeleteComputerCommand;
import com.excilys.cdb.command.ExitCommand;
import com.excilys.cdb.command.ICommand;
import com.excilys.cdb.command.PageableComputerListCommand;
import com.excilys.cdb.command.UpdateComputerCommand;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.IClientService;
import com.excilys.cdb.utils.Utils;

@Transactional
public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static ClassPathXmlApplicationContext context;

	@Autowired
	public static IClientService service;
	static {
		context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		service = context.getBean(IClientService.class);
	}
	public final ICommand[] actions = { new CompanyListCommand(service),
			new ComputerListCommand(service),
			new PageableComputerListCommand(service, 10),
			new ComputerDetailsCommand(service),
			new AddComputerCommand(service),
			new UpdateComputerCommand(service),
			new DeleteComputerCommand(service),
			new DeleteCompanyCommand(service), new ExitCommand() };

	public void displayMenu() {
		System.out.println();
		for (int i = 0; i < actions.length; i++) {
			System.out.println(i + " - " + actions[i].toString());
		}
		System.out.println();
	}

	public void simpleDisplay(Computer c) {
		System.out.println("ID : " + c.getId() + " " + "NAME : " + c.getName());
	}

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
			displayMenu();
			int i = (int) getLong();
			if (i >= actions.length) {
				wrongEntry();
			} else
				actions[i].fetch();
		}
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.loop();
	}
}
