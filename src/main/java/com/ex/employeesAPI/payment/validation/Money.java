package com.ex.employeesAPI.payment.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD,METHOD,CONSTRUCTOR,ANNOTATION_TYPE,PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = MoneyValidator.class)
@Documented
public @interface Money {

    String message() default "Money format must be 0.00 and must be higher than 0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
