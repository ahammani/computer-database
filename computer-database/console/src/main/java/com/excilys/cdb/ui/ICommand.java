package com.excilys.cdb.ui;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.excilys.cdb.main.Main;
import com.excilys.cdb.utils.Utils;

/**
 * Class which represents A command given by user.
 *
 * @author ahammani
 */
public abstract class ICommand {

	/**
	 * Execute command's instruction and display the result.
	 */
	public abstract void fetch();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
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
		return (s.contains("y"));
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	public Timestamp getDate() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
		System.out
				.println("Enter a date with exactly this format : mm-dd-yyyy");
		String date = Main.sc.next();
		while (!Utils.checkDate2(date)) {
			Main.wrongEntry();
			System.out
					.println("Enter a date with exactly this format  : mm-dd-yyyy");
			date = Main.sc.next();
		}
		Date parsedDate = dateFormat.parse(date);
		return (new java.sql.Timestamp(parsedDate.getTime()));

	}

	public LocalDate getLocalDate() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
		System.out
				.println("Enter a date with exactly this format : mm-dd-yyyy");
		String date = Main.sc.next();
		while (!Utils.checkDate2(date)) {
			Main.wrongEntry();
			System.out
					.println("Enter a date with exactly this format : mm-dd-yyyy");
			date = Main.sc.next();
		}
		Date parsedDate = dateFormat.parse(date);
		Instant instant = Instant.ofEpochMilli(parsedDate.getTime());
		LocalDate res = (LocalDateTime.ofInstant(instant,
				ZoneId.systemDefault())).toLocalDate();
		return res;

	}

}
