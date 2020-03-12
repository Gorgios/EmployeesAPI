package com.ex.employeesAPI.payment.service;

import com.ex.employeesAPI.employee.exception.EmployeeNotFoundException;
import com.ex.employeesAPI.employee.model.Employee;
import com.ex.employeesAPI.employee.repository.EmployeeRepository;
import com.ex.employeesAPI.payment.dto.PaymentDto;
import com.ex.employeesAPI.payment.exception.PaymentNotFoundException;
import com.ex.employeesAPI.payment.model.Payment;
import com.ex.employeesAPI.payment.builder.PaymentBuilder;
import com.ex.employeesAPI.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, EmployeeRepository employeeRepository) {
        this.paymentRepository = paymentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> getPaymentsByEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        return paymentRepository.findAllByEmployee(employee);
    }

    @Override
    public Payment findPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentNotFoundException(paymentId));
    }

    @Override
    public Payment addNewPayment(PaymentDto paymentDto, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        return paymentRepository.save(PaymentBuilder.anPayment().build(paymentDto, employee));
    }

    @Override
    public Payment updatePayment(PaymentDto paymentDto, Long paymentId, Long employeeId) {
        if (paymentRepository.findById(paymentId).isEmpty())
            throw new PaymentNotFoundException(paymentId);
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        return paymentRepository.save(PaymentBuilder.anPayment().build(paymentDto, paymentId, employee));

    }

    @Override
    public Double countPaymentAmountFromLastYear(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        LocalDate dateStart = LocalDate.now().minusYears(1);
        List<Payment> payments = paymentRepository.findAllByEmployeeAndDateOfPaymentAfter(employee, dateStart);
        return getAverageAmount(payments);
    }

    @Override
    public Double countPaymentAmountFromPeriod(LocalDate start, LocalDate end, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        List<Payment> payments = paymentRepository.findALlByEmployeeAndDateOfPaymentAfterAndDateOfPaymentBefore(employee, start.minusDays(1), end.plusDays(1));
        return getAverageAmount(payments);
    }

    @Override
    public List<Payment> getPaymentsByListOfEmployees(List<Long> employeesIds) {
        List<Payment> payments = new ArrayList<>();
        for (Long id : employeesIds)
            payments.addAll(paymentRepository.findAllByEmployee(employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException(id))));
       return payments;
    }

    private Double getAverageAmount(List<Payment> payments) {
        if (payments.size() == 0)
            return 0.0;
        double sum = 0;
        for (Payment payment : payments)
            sum += payment.getAmount();
        return sum / payments.size();
    }
}
