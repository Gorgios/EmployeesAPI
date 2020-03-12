package com.ex.employeesAPI.payment.repository;

import com.ex.employeesAPI.employee.model.Employee;
import com.ex.employeesAPI.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByEmployee(Employee employee);

    List<Payment> findAllByEmployeeAndDateOfPaymentAfter(Employee employee, LocalDate start);

    List<Payment> findALlByEmployeeAndDateOfPaymentAfterAndDateOfPaymentBefore(Employee employee, LocalDate start, LocalDate end);

}
