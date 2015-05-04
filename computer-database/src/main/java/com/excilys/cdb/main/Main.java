package com.excilys.cdb.main;

import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.ui.*;
import com.excilys.cdb.utils.Utils;

public class Main {
	public static CLI cli = new CLI();
	public static Scanner sc = new Scanner(System.in);
	public static ComputerService computerService;
	public static CompanyService companyService;

	public static ClassPathXmlApplicationContext context;
	static {
		context = new ClassPathXmlApplicationContext(
				"classpath:application-context.xml");
		computerService = context.getBean(ComputerService.class);
		companyService = context.getBean(CompanyService.class);
		System.out.println(computerService.toString());
	}
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

	public static void main(String[] args) {
		while (true) {
			cli.displayMenu();
			int i = (int) getLong();
			if (i >= actions.length) {
				wrongEntry();
			} else
				actions[i].fetch();
		}
	}
}
