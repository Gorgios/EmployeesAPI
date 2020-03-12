package com.ex.employeesAPI.employee.employee;


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
    private Set<EmployeeStatus> workingEmployeeStatuses(){
        return Set.of(EmployeeStatus.WORKING, EmployeeStatus.ON_VACATION, EmployeeStatus.SICK_LEAVE);
    }
    public boolean isWorking() {
        return  workingEmployeeStatuses().contains(this);
    }

}
