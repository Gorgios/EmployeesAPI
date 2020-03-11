package com.ex.employeesAPI.employee.employeeStatus;


import com.ex.employeesAPI.employee.model.Employee;

import java.util.Set;

public enum EmployeeStatus {
    WORKING("Working"),
    ON_VACATION ("On vacation"),
    FIRED ("Fired"),
    SICK_LEAVE ("Sick leave"),
    RETIRED ("Retired");

    private String description;
    EmployeeStatus(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
    public static Set<EmployeeStatus> notWorkingEmployeeStatuses(){
        return Set.of(EmployeeStatus.FIRED, EmployeeStatus.RETIRED);
    }
    public boolean isWorking() {
        return  !(notWorkingEmployeeStatuses().contains(this));
    }

}
