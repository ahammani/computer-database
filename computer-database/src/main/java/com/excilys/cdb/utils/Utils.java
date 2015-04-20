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
		Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
		Matcher m = p.matcher(d);
		return m.matches();
	}

}
