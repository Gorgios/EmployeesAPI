package com.ex.employeesAPI.employee.service;

import com.ex.employeesAPI.employee.dto.EmployeeDto;
import com.ex.employeesAPI.employee.exception.EmployeeNotFoundException;
import com.ex.employeesAPI.employee.model.Employee;
import com.ex.employeesAPI.employee.builder.EmployeeBuilder;
import com.ex.employeesAPI.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Employee employee = EmployeeBuilder.anEmployee().build(employeeDto);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(EmployeeDto employeeDto, Long employeeId) {
        if( employeeRepository.findById(employeeId).isEmpty())
            throw new EmployeeNotFoundException(employeeId);
        Employee employee = EmployeeBuilder.anEmployee().build(employeeDto,employeeId);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAllWorking() {
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> working = new ArrayList<>();
        for (Employee e: employees)
            if (e.getEmployeeStatus().isWorking())
                working.add(e);
        return working;
    }

    @Override
    public List<Employee> findAllNotWorking() {
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> notWorking = new ArrayList<>();
        for (Employee e: employees)
            if (e.getEmployeeStatus().isWorking())
                notWorking.add(e);
        return  notWorking;
    }
}
