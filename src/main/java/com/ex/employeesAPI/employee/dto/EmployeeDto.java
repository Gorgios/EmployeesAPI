package com.ex.employeesAPI.employee.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String photoUrl;
    private LocalDate dateOfBrith;
    private LocalDate dateOfHire;

}
