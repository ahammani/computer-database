package ui;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.Main;

// TODO: Auto-generated Javadoc

/**
 * Class which represents A command given by user.
 *
 * @author ahammani
 */
public abstract class Command {

	/**
	 * Execute command's instruction and display the result.
	 */
	public abstract void fetch();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();

	/**
	 * Represents a boolean user choice.
	 *
	 * @param arg
	 *            the arg
	 * @return true, if successful
	 */
	public boolean chooseArgs(String arg) {
		System.out.println("Do you want " + arg + "? (y/n)");
		String s = Main.sc.next();
		while (s.length() != 1 && !(s.contains("y") || s.contains("n"))) {
			Main.wrongEntry();
			System.out.print("Press 'y' for yes and 'n' for no >");
		}
		return (s.contains("y")) ? true : false;
	}

	/**
	 * Check if the string d represents a date with yyyy-MM-dd form
	 * 
	 * @param d
	 *            the string which represent a date
	 * @return true if successful
	 */
	public boolean checkDate(String d) {
		Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
		Matcher m = p.matcher(d);
		return m.matches();
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	public Timestamp getDate() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS");
		System.out
				.println("Enter a date with exactly this format (it's very important) : yyyy-MM-dd");
		String date = Main.sc.next();
		while (!checkDate(date)) {
			Main.wrongEntry();
			System.out
					.println("Enter a date with exactly this format (it's very important) : yyyy-MM-dd");
			date = Main.sc.next();
		}
		date += " 00:00:00.000";
		Date parsedDate = dateFormat.parse(date);
		return (new java.sql.Timestamp(parsedDate.getTime()));

	}
}
