package ui;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.Main;

public abstract class Command {
	public abstract void fetch();

	public abstract String toString();

	public boolean chooseArgs(String arg) {
		System.out.println("Do you want " + arg + "? (y/n)");
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
}
