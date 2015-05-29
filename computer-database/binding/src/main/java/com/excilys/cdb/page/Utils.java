package com.excilys.cdb.page;

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
}
