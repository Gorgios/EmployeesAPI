package com.ex.employeesAPI.payment.service;

import com.ex.employeesAPI.employee.model.Employee;
import com.ex.employeesAPI.payment.dto.PaymentDto;
import com.ex.employeesAPI.payment.model.Payment;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayments();

    List<Payment> getPaymentsByEmployeeId(Long employeeId);

    Payment findPaymentById(Long paymentId);

    Payment addNewPayment(PaymentDto paymentDto, Long employeeId);

    Payment updatePayment(PaymentDto paymentDto, Long paymentId, Long employeeId);

    Double countPaymentAmountFromLastYear(Long employeeId);

    Double countPaymentAmountFromPeriod(LocalDate start, LocalDate end, Long employeeId);

    List<Payment> getPaymentsByListOfEmployees(List<Long> employeesIds);
}
