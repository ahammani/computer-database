package com.excilys.cdb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	public static boolean isNumber(String s) {
		if (s == null || s == "") {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) < 48 || s.charAt(i) > 57) {
				return false;
			}
		}
		return true;
	}

	public static int StringToInt(String s, int bad) {
		if (Utils.isNumber(s))
			return Integer.parseInt(s);
		else
			return bad;
	}

	/**
	 * Check if the string d represents a date with yyyy-MM-dd form
	 * 
	 * @param d
	 *            the string which represent a date
	 * @return true if successful
	 */
	public static boolean checkDate(String d) {
		Pattern p = Pattern
				.compile("[0-9]{4}-(0[1-9]|1[1-2])-([0-2][0-9]|3[01])");
		Matcher m = p.matcher(d);
		Pattern month31 = Pattern
				.compile("[0-9]{4}-[01|03|05|07|08|10|12]-([0-2][0-9]|3[01])");
		Pattern month30 = Pattern
				.compile("[0-9]{4}-[04|06|08|09|11]-([0-2][0-9]|30)");
		Pattern february = Pattern.compile("[0-9]{4}-02-[0-2][0-9]");
		if (m.matches()) {
			String[] date = d.split("-");
			int year = Integer.parseInt(date[0]);
			int month = Integer.parseInt(date[1]);
			int day = Integer.parseInt(date[2]);
			// Timestamp limit 2038-01-19 should not be here
			if (year == 2038 && ((month == 01 && day > 19) || month > 01)
					|| year > 2038)
				return false;
			// bissextile year
			if (month == 02
					&& !((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
					&& day == 29)
				return false;
			else
				return month31.matcher(d).matches()
						|| month30.matcher(d).matches()
						|| february.matcher(d).matches();
		} else
			return false;
	}
}
