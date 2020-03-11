package com.ex.employeesAPI.employee.repository;

import com.ex.employeesAPI.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findAll();
    Optional<Employee> findById(Long id);
}
