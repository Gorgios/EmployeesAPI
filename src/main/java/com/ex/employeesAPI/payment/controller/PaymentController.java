package com.ex.employeesAPI.payment.controller;

import com.ex.employeesAPI.payment.dto.PaymentDto;
import com.ex.employeesAPI.payment.model.Payment;
import com.ex.employeesAPI.payment.service.PaymentService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("{paymentId}")
    public Payment getPaymentById(@PathVariable Long paymentId) {
        return paymentService.findPaymentById(paymentId);
    }

    @PostMapping
    public Payment addNewPayment(@RequestBody PaymentDto paymentDto, @RequestParam Long employeeId) {
        return paymentService.addNewPayment(paymentDto, employeeId);
    }

    @PutMapping("{paymentId}")
    public Payment updatePayment(@RequestBody PaymentDto paymentDto, @PathVariable Long paymentId, @RequestParam Long employeeId) {
        return paymentService.updatePayment(paymentDto, paymentId, employeeId);
    }

    @GetMapping("/employees_payments")
    public List<Payment> paymentsOffEmployees (@RequestParam List<Long> employeesIds){
        return paymentService.getPaymentsByListOfEmployees(employeesIds);
    }

    @GetMapping("/employee_payments/{employeeId}")
    public List<Payment> employeePayments(@PathVariable Long employeeId) {
        return paymentService.getPaymentsByEmployeeId(employeeId);
    }

    @GetMapping("/amount_last_year/{employeeId}")
    public Double lastYearAmount(@PathVariable Long employeeId) {
        return paymentService.countPaymentAmountFromLastYear(employeeId);
    }

    @GetMapping("/amount_by_period/{employeeId}")
    public Double amountByPeriod(@PathVariable Long employeeId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return paymentService.countPaymentAmountFromPeriod(start, end, employeeId);

    }

}
