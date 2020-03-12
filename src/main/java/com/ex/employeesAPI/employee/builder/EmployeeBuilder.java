package com.ex.employeesAPI.employee.builder;

import com.ex.employeesAPI.employee.dto.EmployeeDto;
import com.ex.employeesAPI.employee.employeeStatus.EmployeeStatus;
import com.ex.employeesAPI.employee.model.Employee;

import java.time.LocalDate;

public final class EmployeeBuilder {

    private EmployeeBuilder() {
    }

    public static EmployeeBuilder anEmployee() {
        return new EmployeeBuilder();
    }

    // return new employee from dto
    public Employee build(EmployeeDto e){
        return getEmployee(e);
    }
    // return updated employee from dto and id
    public Employee build(EmployeeDto e, Long id){
        Employee employee = getEmployee(e);
        employee.setId(id);
        return employee;
    }

    private Employee getEmployee(EmployeeDto e) {
        Employee employee = new Employee();
        employee.setFirstName(e.getFirstName());
        employee.setLastName(e.getLastName());
        employee.setPhotoUrl(e.getPhotoUrl());
        employee.setDateOfBirth(e.getDateOfBirth());
        employee.setDateOfHire(getHireDate(e.getDateOfHire()));
        employee.setEmployeeStatus(getEmployeeStatus(e.getEmployeeStatus()));
        return  employee;
    }

    private LocalDate getHireDate(LocalDate dateOfHire){
        if (dateOfHire != null)
            return  dateOfHire;
        return LocalDate.now();
    }
    private EmployeeStatus getEmployeeStatus(EmployeeStatus employeeStatus){
        if (employeeStatus != null)
            return employeeStatus;
        return EmployeeStatus.WORKING;
    }
}
