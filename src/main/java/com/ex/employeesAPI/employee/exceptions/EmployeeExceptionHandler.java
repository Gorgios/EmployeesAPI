package com.ex.employeesAPI.employee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String getEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> getConstraintViolationException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        errors.add("Cannot add/update");
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }
        return  errors;
    }


    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> getHttpMessageConversionException (HttpMessageConversionException ex)
    {
        List<String> errors = new ArrayList<>();
        errors.add("Cannot add/update");
        if (ex.getMessage().contains("java.time.LocalDate"))
            errors.add("Date must be 'YYYY-MM-DD'");
        if (ex.getMessage().contains("EmployeeStatus"))
            errors.add("Employee status can be only : [WORKING, FIRED, RETIRED, SICK_LEAVE, ON_VACATION]");
        if (ex.getMessage().contains("Double"))
            errors.add("Price must be numeric'");
        return  errors;
    }
}
