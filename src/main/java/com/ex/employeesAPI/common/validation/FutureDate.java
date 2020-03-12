package com.ex.employeesAPI.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD,METHOD,CONSTRUCTOR,ANNOTATION_TYPE,PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = FutureDateValidator.class)
@Documented
public @interface FutureDate {

    String message() default "Date cannot be future";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
