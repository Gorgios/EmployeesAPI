package com.ex.employeesAPI.payment.exception;

import java.time.LocalDate;

public class DateStartIsAfterDayEndException extends Exception{
    public DateStartIsAfterDayEndException(LocalDate start, LocalDate end){
        super(start + " is after " + end + ". Start date has to be before end date");
    }
}
