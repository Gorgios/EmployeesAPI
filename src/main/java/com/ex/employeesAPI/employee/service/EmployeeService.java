package com.ex.employeesAPI.employee.service;

import com.ex.employeesAPI.employee.dto.EmployeeDto;
import com.ex.employeesAPI.employee.model.Employee;

import java.awt.print.Book;
import java.util.List;

public interface EmployeeService {

    Employee findById(Long id);

    List<Employee> findAll();

    Employee addNewEmployee(EmployeeDto employeeDto);

    Employee updateEmployee(EmployeeDto employeeDto, Long employeeId);

}
