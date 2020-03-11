package com.ex.employeesAPI.employee.service;

import com.ex.employeesAPI.employee.dto.EmployeeDto;
import com.ex.employeesAPI.employee.exceptions.EmployeeNotFoundException;
import com.ex.employeesAPI.employee.model.Employee;
import com.ex.employeesAPI.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findById(Long id) {
       return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee addNewEmployee(EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public Employee updateEmployee(EmployeeDto employeeDto, Long employeeId) {
        return null;
    }
}
