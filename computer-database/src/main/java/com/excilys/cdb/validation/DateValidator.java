package com.excilys.cdb.validation;

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
		return d.matches(messageSource.getMessage("validation.pattern", null,
				LocaleContextHolder.getLocale()));
	}

}
