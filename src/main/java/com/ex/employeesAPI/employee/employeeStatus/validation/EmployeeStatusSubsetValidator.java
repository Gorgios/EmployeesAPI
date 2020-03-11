package com.ex.employeesAPI.employee.employeeStatus.validation;

import com.ex.employeesAPI.employee.employeeStatus.EmployeeStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EmployeeStatusSubsetValidator implements ConstraintValidator<EmployeeStatusSubset, EmployeeStatus> {
    private EmployeeStatus[] subset;

    @Override
    public void initialize(EmployeeStatusSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(EmployeeStatus value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset)
                .contains(value);
    }
}
