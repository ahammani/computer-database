package com.excilys.cdb.main;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.service.ICompanyService;
import com.excilys.cdb.service.IComputerService;
import com.excilys.cdb.ui.AddComputerCommand;
import com.excilys.cdb.ui.CLI;
import com.excilys.cdb.ui.CompanyListCommand;
import com.excilys.cdb.ui.ComputerDetailsCommand;
import com.excilys.cdb.ui.ComputerListCommand;
import com.excilys.cdb.ui.DeleteCompanyCommand;
import com.excilys.cdb.ui.DeleteComputerCommand;
import com.excilys.cdb.ui.ExitCommand;
import com.excilys.cdb.ui.ICommand;
import com.excilys.cdb.ui.PageableComputerListCommand;
import com.excilys.cdb.ui.UpdateComputerCommand;
import com.excilys.cdb.utils.Utils;

@Transactional
public class Main {
	public static CLI cli = new CLI();
	public static Scanner sc = new Scanner(System.in);
	public static IComputerService computerService;
	public static ICompanyService companyService;

	public static ClassPathXmlApplicationContext context;
	static {
		context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		computerService = context.getBean(IComputerService.class);
		companyService = context.getBean(ICompanyService.class);
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
