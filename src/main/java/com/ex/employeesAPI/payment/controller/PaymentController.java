package com.ex.employeesAPI.payment.controller;

import com.ex.employeesAPI.payment.dto.PaymentDto;
import com.ex.employeesAPI.payment.model.Payment;
import com.ex.employeesAPI.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.findPaymentById(id);
    }

    @PostMapping
    public Payment addNewPayment(@RequestBody PaymentDto paymentDto, @RequestParam Long employeeId) {
        return paymentService.addNewPayment(paymentDto, employeeId);
    }

    @PutMapping("{id}")
    public Payment updatePayment(@RequestBody PaymentDto paymentDto, @PathVariable Long id, @RequestParam Long employeeId) {
        return paymentService.updatePayment(paymentDto, id, employeeId);
    }
}
