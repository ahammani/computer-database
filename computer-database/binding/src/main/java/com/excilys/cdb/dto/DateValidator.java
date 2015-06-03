package com.excilys.cdb.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Validator for custom Date annotation. Also check Timestamp limit(2038-01-19)
 * 
 * @author ahammani
 *
 */
public class DateValidator implements ConstraintValidator<Date, String> {
	private static List<Integer> MONTH30 = new ArrayList<Integer>(
			Arrays.asList(2, 4, 6, 9, 11));

	@Autowired
	private MessageSource messageSource;

	@Override
	public void initialize(Date arg0) {
	}

	@Override
	public boolean isValid(String d, ConstraintValidatorContext contraintContext) {
		if (d == null || d == "") {
			return true;
		}
		Locale local = LocaleContextHolder.getLocale();
		// date format ex: dd-MM-yyyy
		String format = messageSource.getMessage("validation.format", null,
				local);
		// regexp pattern
		String pattern = messageSource.getMessage("validation.pattern", null,
				local);
		boolean verif = d.matches(pattern);
		if (verif) {
			String[] date = d.split("-");
			int year = Integer.parseInt(date[2]);

			int month = toInt(d, format, "MM");
			int day = toInt(d, format, "dd");
			// Timestamp limit 2038-01-19 should not be here
			if (year == 2038 && ((month == 01 && day > 19) || month > 01)
					|| year > 2038) {
				return false;
			}
			if (year <= 1970)
				return false;
			// bissextile year
			if (month == 02
					&& !((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
					&& day == 29) {
				return false;
			}
			if (day > 30 && MONTH30.contains(month) || day > 31 || month > 12) {
				return false;
			}
		}

		return verif;
	}

	private int toInt(String date, String format, String match) {
		Pattern p = Pattern.compile(match);
		Matcher m = p.matcher(format);
		if (m.find()) {
			return Integer.parseInt(date.substring(m.start(), m.end()));
		}

		return -1;
	}

}
