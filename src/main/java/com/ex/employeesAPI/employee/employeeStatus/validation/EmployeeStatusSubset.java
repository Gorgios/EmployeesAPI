package com.ex.employeesAPI.employee.employeeStatus.validation;

import com.ex.employeesAPI.employee.employeeStatus.EmployeeStatus;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EmployeeStatusSubsetValidator.class)
public @interface EmployeeStatusSubset {
    EmployeeStatus [] anyOf() default {EmployeeStatus.ON_VACATION, EmployeeStatus.WORKING, EmployeeStatus.FIRED, EmployeeStatus.RETIRED, EmployeeStatus.SICK_LEAVE};
    String message() default "Must be any of {anyOf}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}