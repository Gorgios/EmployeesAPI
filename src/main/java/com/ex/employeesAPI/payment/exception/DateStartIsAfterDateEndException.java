package com.ex.employeesAPI.payment.exception;

import java.time.LocalDate;

public class DateStartIsAfterDateEndException extends Exception{
    public DateStartIsAfterDateEndException(LocalDate start, LocalDate end){
        super(start + " is after " + end + ". Start date has to be before end date");
    }
}
