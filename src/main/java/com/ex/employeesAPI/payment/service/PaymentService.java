package com.ex.employeesAPI.payment.service;
import com.ex.employeesAPI.payment.dto.PaymentDto;
import com.ex.employeesAPI.payment.exception.DateStartIsAfterDateEndException;
import com.ex.employeesAPI.payment.model.Payment;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    List<Payment> findAll();

    List<Payment> findAllByEmployee(Long employeeId);

    Payment findById(Long paymentId);

    Payment addNewPayment(PaymentDto paymentDto, Long employeeId);

    Payment updatePayment(PaymentDto paymentDto, Long paymentId, Long employeeId);

    Double countPaymentAmountFromLastYear(Long employeeId);

    Double countPaymentAmountFromPeriod(LocalDate start, LocalDate end, Long employeeId) throws DateStartIsAfterDateEndException;

    List<Payment> findAllByEmployees(List<Long> employeesIds);
}
