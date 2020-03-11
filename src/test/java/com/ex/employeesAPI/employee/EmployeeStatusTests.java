package com.ex.employeesAPI.employee;

import com.ex.employeesAPI.employee.employeeStatus.EmployeeStatus;
import com.ex.employeesAPI.employee.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
public class EmployeeStatusTests {

    @Test
    public void testCheckIsWorking() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("xxx");
        employee.setLastName("xxx");
        employee.setDateOfBirth(LocalDate.of(1990, 10, 10));
        employee.setDateOfHire(LocalDate.of(2015, 11, 01));
        employee.setEmployeeStatus(EmployeeStatus.ON_VACATION);
        Assert.assertTrue(employee.getEmployeeStatus().isWorking());
        employee.setEmployeeStatus(EmployeeStatus.FIRED);
        Assert.assertFalse(employee.getEmployeeStatus().isWorking());
    }
    @Test
    public void testStatusReturnGoodString() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("xxx");
        employee.setLastName("xxx");
        employee.setDateOfBirth(LocalDate.of(1990, 10, 10));
        employee.setDateOfHire(LocalDate.of(2015, 11, 01));
        employee.setEmployeeStatus(EmployeeStatus.ON_VACATION);
        Assert.assertEquals("On vacation",employee.getEmployeeStatus().toString());
    }
}
