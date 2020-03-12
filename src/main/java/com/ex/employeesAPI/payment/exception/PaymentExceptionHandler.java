package com.ex.employeesAPI.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaymentExceptionHandler {

    @ExceptionHandler(PaymentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String getPaymentNotFoundException(PaymentNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(DateStartIsAfterDateEndException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String getDateStartIsAfterDayEndException (DateStartIsAfterDateEndException ex){
        return ex.getMessage();
    }
}
