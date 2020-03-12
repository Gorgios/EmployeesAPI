package com.ex.employeesAPI.employee.controller;

import com.ex.employeesAPI.employee.dto.EmployeeDto;
import com.ex.employeesAPI.employee.model.Employee;
import com.ex.employeesAPI.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping
    public Employee addNewEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.addNewEmployee(employeeDto);
    }

    @GetMapping("/employed")
    public List<Employee> getAllEmployed() {
        return employeeService.findAllWorking();
    }

    @GetMapping("/unemployed")
    public List<Employee> getAllUnemployed() {
        return employeeService.findAllNotWorking();
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long id) {
        return employeeService.updateEmployee(employeeDto, id);
    }
}
