package com.ex.employeesAPI.employee.repository;

import com.ex.employeesAPI.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findAll();
    Employee findById();
}
