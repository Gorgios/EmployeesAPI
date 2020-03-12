package com.ex.employeesAPI.employee.validation;

import com.ex.employeesAPI.employee.model.Employee;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidAfterDateValidator implements ConstraintValidator<ValidAfterDate, Employee> {

    @Override
    public void initialize(ValidAfterDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(Employee employee, ConstraintValidatorContext context) {
        if (employee == null) {
            return true;
        }

        return employee.getDateOfBirth().compareTo(employee.getDateOfHire()) < 0;
    }
}