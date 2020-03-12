package com.ex.employeesAPI.employee.model;

import com.ex.employeesAPI.common.validation.FutureDate;
import com.ex.employeesAPI.employee.employeeStatus.EmployeeStatus;
import com.ex.employeesAPI.payment.model.Payment;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView
    @Column(name = "employee_id")
    private Long id;
    @NotNull(message = "First name cannot be null")
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    @NotNull(message = "Last name cannot be null")
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;
    @URL(message = "Photo link has to be URL")
    private String photoUrl;
    @NotNull(message = "Date of birth cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureDate
    private LocalDate dateOfBirth;
    @NotNull(message = "Date of hire cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureDate
    private LocalDate dateOfHire;
    @NotNull(message = "Employee status cannot be null")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;
    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Payment> payments;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfHire() {
        return dateOfHire;
    }

    public void setDateOfHire(LocalDate dateOfHire) {
        this.dateOfHire = dateOfHire;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }
}
