package com.ex.employeesAPI.employee.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class EmployeeDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String photoUrl;
    @NotNull
    private LocalDate dateOfBrith;
    @NotNull
    private LocalDate dateOfHire;

}
