package com.excilys.cdb.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Mapper between LocalDate and String
 * 
 * @author ahammani
 *
 */
@Component
public class TimeMapper {
	@Autowired
	private MessageSource messageSource;

	@SuppressWarnings("unused")
	public String LocalDateToString(LocalDate t) {
		if (t == null) {
			return "";
		}
		String format = messageSource.getMessage("validation.format", null,
				LocaleContextHolder.getLocale());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		if (t != null) {
			return t.format(formatter);
		}
		return "";
	}

	public LocalDate StringToLocalDate(String s) {
		if (s == null || s.isEmpty())
			return null;
		String format = messageSource.getMessage("validation.format", null,
				LocaleContextHolder.getLocale());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDate.parse(s, formatter);
	}
}
