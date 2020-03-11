package com.ex.employeesAPI.payment.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PaymentDto {
    private LocalDate dateOfPayment;
    private Double amount;
    private Long employeeId;

}
