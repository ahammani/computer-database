package ui;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import data.Computer;
import main.Main;

public class AddComputerCommand extends Command {

	public boolean chooseArgs(String arg) {
		System.out.println("Do you want add " + arg + "? (y/n)");
		String s = Main.sc.next();
		while (s.length() != 1 && !(s.contains("y") || s.contains("n"))) {
			Main.wrongEntry();
			System.out.print("Press 'y' for yes and 'n' for no >");
		}
		return (s.contains("y")) ? true : false;
	}

	public Timestamp getDate() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS");
		System.out
				.println("Enter a date with this format (it's very important) : yyyy-MM-dd");
		String date = Main.sc.next();
		date += " 00:00:00.000";
		Date parsedDate = dateFormat.parse(date);
		return (new java.sql.Timestamp(parsedDate.getTime()));

	}

	@Override
	public void fetch() {

		Integer id = null;
		Timestamp intro = null, dis = null;

		System.out.println("Enter a computer name >");
		String name = Main.sc.next();
		try {
			if (chooseArgs("introduced date")) {
				intro = getDate();
			}
			if (chooseArgs("discontinued date")) {
				dis = getDate();
			}
			if (chooseArgs("company id")) {
				id = Main.getInt();
			}
			Computer comp = new Computer(name, intro, dis, id);
			Main.computers.create(comp);
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
