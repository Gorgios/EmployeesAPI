package com.ex.employeesAPI.employee.employeeStatus;


public enum EmployeeStatus {
    WORKING("Working"),
    ON_VACATION ("On vacation"),
    FIRED ("Fired"),
    SICK_LEAVE ("Sick leave"),
    RETIRED ("Retired");

    private String text;
    EmployeeStatus(String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public boolean isWorking() {
        return this != EmployeeStatus.RETIRED && this != EmployeeStatus.FIRED;
    }

}
