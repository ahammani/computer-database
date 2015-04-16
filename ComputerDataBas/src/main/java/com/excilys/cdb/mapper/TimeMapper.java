package com.excilys.cdb.mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeMapper {

	public static Timestamp LocalDateTimeToTimestamp(LocalDateTime ldt) {
		if (ldt != null)
			return Timestamp.valueOf(ldt);
		else
			return null;
	}

	public static LocalDateTime TimestampToLocalDateTime(Timestamp ts) {
		if (ts != null)
			return ts.toLocalDateTime();
		else
			return null;
	}

	public static String LocalDateTimeToString(LocalDateTime t) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (t != null) {
			return t.format(formatter);
		}
		return "";
	}

}
