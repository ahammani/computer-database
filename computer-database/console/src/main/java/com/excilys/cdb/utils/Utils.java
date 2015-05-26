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

	public static int stringToInt(String s, int bad) {
		if (s == null || s == "")
			return bad;
		Integer max = Integer.MAX_VALUE;
		int max_size = max.toString().length();
		if (max_size < s.length()
				|| (max_size == s.length() && max.toString().compareTo(s) < 0))
			return bad;
		if (Utils.isNumber(s))
			return Integer.parseInt(s);
		else
			return bad;
	}

	public static long stringToLong(String s, long bad) {
		if (s == null || s == "")
			return bad;
		Long max = Long.MAX_VALUE;
		int max_size = max.toString().length();
		if (max_size < s.length()
				|| (max_size == s.length() && max.toString().compareTo(s) < 0))
			return bad;
		else if (Utils.isNumber(s))
			return Long.parseLong(s);
		else
			return bad;
	}

	/**
	 * Check if the string d represents a date with mm-dd-yyyy form
	 * 
	 * @param d
	 *            the string which represent a date
	 * @return true if successful
	 */
	public static boolean checkDate2(String d) {
		if (d == null)
			return false;
		d = d.split(" ")[0];
		Pattern p = Pattern
				.compile("(0[1-9]|1[1-2])-([0-2][0-9]|3[01])-[0-9]{4}");
		Matcher m = p.matcher(d);
		Pattern month31 = Pattern
				.compile("(01|03|05|07|08|10|12)-([0-2][0-9]|3[01])-[0-9]{4}");
		Pattern month30 = Pattern
				.compile("(04|06|08|09|11)-([0-2][0-9]|30)-[0-9]{4}");
		Pattern february = Pattern.compile("02-[0-2][0-9]-[0-9]{4}");
		if (m.matches()) {
			String[] date = d.split("-");
			int year = Integer.parseInt(date[2]);
			int month = Integer.parseInt(date[0]);
			int day = Integer.parseInt(date[1]);
			// Timestamp limit 2038-01-19 should not be here
			if (year == 2038 && ((month == 01 && day > 19) || month > 01)
					|| year > 2038) {
				System.out.println("Wrong entry : " + d);
				return false;
			}
			if (year <= 1970)
				return false;
			// bissextile year
			if (month == 02
					&& !((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
					&& day == 29) {
				return false;
			} else {
				Matcher m2 = month31.matcher(d);
				Matcher m3 = month30.matcher(d);
				Matcher m4 = february.matcher(d);
				return (m2.matches() || m3.matches() || m4.matches());
			}
		} else {
			return false;
		}
	}
}
