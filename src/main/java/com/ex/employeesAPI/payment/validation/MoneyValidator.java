package com.ex.employeesAPI.payment.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolationException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

public class MoneyValidator implements ConstraintValidator<Money, Double> {
    @Override
    public void initialize(Money constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double amount, ConstraintValidatorContext constraintValidatorContext) {
        if (amount < 0)
            return false;
        String amountString = amount.toString();
        if (amountString.contains("."))
            return (amountString.indexOf(".") > amountString.length() - 4 && amountString.indexOf(".") < amountString.length() - 1);
        return true;
    }


}
