package com.ex.employeesAPI.payment.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PaymentDto {
    private LocalDate dateOfPayment;
    private Double amount;

    public LocalDate getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
