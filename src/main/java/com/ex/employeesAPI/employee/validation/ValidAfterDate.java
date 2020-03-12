package com.ex.employeesAPI.employee.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidAfterDateValidator.class })
@Documented
public @interface ValidAfterDate {


    String message() default "The born date need to be before hire date" ;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}