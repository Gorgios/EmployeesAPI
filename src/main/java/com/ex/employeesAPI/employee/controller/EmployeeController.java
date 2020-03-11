package com.ex.employeesAPI.employee.controller;

import com.ex.employeesAPI.employee.dto.EmployeeDto;
import com.ex.employeesAPI.employee.exceptions.EmployeeNotFoundException;
import com.ex.employeesAPI.employee.model.Employee;
import com.ex.employeesAPI.employee.repository.EmployeeRepository;
import com.ex.employeesAPI.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Employee getEmployeeById (@PathVariable Long id){
        return employeeService.findById(id);
    }
    @RequestMapping(method = RequestMethod.POST)
    public Employee addNewEmployee (@RequestBody EmployeeDto employeeDto){
        return employeeService.addNewEmployee(employeeDto);
    }
}
