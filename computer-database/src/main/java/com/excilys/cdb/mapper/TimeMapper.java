package com.excilys.cdb.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.excilys.cdb.utils.Utils;

public class TimeMapper {

	public static String LocalDateToString(LocalDate t) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (t != null) {
			return t.format(formatter);
		}
		return "";
	}

	public static LocalDate StringToLocalDate(String s) {
		if (Utils.checkDate(s)) {
			return LocalDate.parse(s);
		} else
			return null;
	}

}
