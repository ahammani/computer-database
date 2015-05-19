package com.excilys.cdb.controller.dto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Custom annotation for date checking
 * 
 * @author ahammani
 *
 */
// Only field can annotated with Date
@Target(value = { ElementType.FIELD })
// annotations of this type will be available at runtime
@Retention(RetentionPolicy.RUNTIME)
// Validator to be used to validate element with @Date
@Constraint(validatedBy = DateValidator.class)
public @interface Date {

	String message() default "{validation.invalidFormat}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
