package com.excilys.cdb.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.excilys.cdb.utils.Utils;

public class TimeMapper {

	public static String LocalDateTimeToString(LocalDateTime t) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (t != null) {
			return t.format(formatter);
		}
		return "";
	}

	public static LocalDateTime StringToLocalDateTime(String s) {
		if (Utils.checkDate(s)) {
			// DateTimeFormatter formatter = DateTimeFormatter
			// .ofPattern("yyyy-MM-dd");
			return LocalDateTime.parse(s + "T00:00:00");
		} else
			return null;
	}

}
