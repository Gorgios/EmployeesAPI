package com.ex.employeesAPI.payment.builder;


import com.ex.employeesAPI.employee.model.Employee;
import com.ex.employeesAPI.payment.dto.PaymentDto;
import com.ex.employeesAPI.payment.model.Payment;
import java.time.LocalDate;
// Its builder which build new Payment from PaymentDTO

public final class PaymentBuilder {
    private PaymentBuilder() {
    }

    public static PaymentBuilder anPayment() {
        return new PaymentBuilder();
    }

    public Payment build(PaymentDto paymentDto, Employee employee) {
        Payment payment = new Payment();
        payment.setAmount(paymentDto.getAmount());
        payment.setDateOfPayment(getDateOfPayment(paymentDto.getDateOfPayment()));
        payment.setEmployee(employee);
        return payment;
    }

    public Payment build(PaymentDto paymentDto, Long id, Employee employee) {
        Payment payment = new Payment();
        payment.setId(id);
        payment.setAmount(paymentDto.getAmount());
        payment.setDateOfPayment(getDateOfPayment(paymentDto.getDateOfPayment()));
        payment.setEmployee(employee);
        return payment;
    }

    private LocalDate getDateOfPayment(LocalDate dateOfPayment) {
        if (dateOfPayment != null)
            return dateOfPayment;
        return LocalDate.now();
    }
}
