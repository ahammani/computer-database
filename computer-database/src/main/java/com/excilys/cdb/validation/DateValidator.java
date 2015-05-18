package com.excilys.cdb.validation;

import java.util.Locale;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class DateValidator implements ConstraintValidator<Date, String> {

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
		boolean verif = d.matches(messageSource.getMessage(
				"validation.pattern", null, local));
		if (verif) {
			String[] date = d.split("-");
			int year = Integer.parseInt(date[2]);

			int month = -1, day = -1;
			if (local.equals(Locale.FRENCH)) {
				month = Integer.parseInt(date[1]);
				day = Integer.parseInt(date[0]);
			}
			if (local.equals(Locale.ENGLISH)) {
				month = Integer.parseInt(date[0]);
				day = Integer.parseInt(date[1]);
			}
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
		}

		return verif;
	}

}
