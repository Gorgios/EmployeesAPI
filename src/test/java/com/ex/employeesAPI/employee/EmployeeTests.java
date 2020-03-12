package com.ex.employeesAPI.employee;

import com.ex.employeesAPI.employee.dto.EmployeeDto;
import com.ex.employeesAPI.employee.employee.EmployeeStatus;
import com.ex.employeesAPI.employee.exception.EmployeeNotFoundException;
import com.ex.employeesAPI.employee.model.Employee;
import com.ex.employeesAPI.employee.service.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

import static org.junit.Assert.fail;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("com.ex.employeesAPI.employee.service")
@RunWith(SpringRunner.class)
public class EmployeeTests {
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testAddEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("xxx");
        employeeDto.setLastName("xxx");
        employeeDto.setDateOfBirth(LocalDate.of(1990, 10, 10));
        employeeService.addNewEmployee(employeeDto);

        Assert.assertEquals(employeeService.findAll().size(), 1);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testAddEmployeeWithEmptyName() throws ConstraintViolationException {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("");
        employeeDto.setLastName("xxx");
        employeeDto.setDateOfBirth(LocalDate.of(1990, 10, 10));

        fail(String.valueOf(employeeService.addNewEmployee(employeeDto)));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testAddEmployeeWithBirthDateGreaterThanHireDate() throws ConstraintViolationException {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("");
        employeeDto.setLastName("xxx");
        employeeDto.setDateOfBirth(LocalDate.of(1990, 10, 10));
        employeeDto.setDateOfHire(LocalDate.of(1981, 10, 10));

        fail(String.valueOf(employeeService.addNewEmployee(employeeDto)));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testAddEmployeeWithBirthHireDayInFuture() throws ConstraintViolationException {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("");
        employeeDto.setLastName("xxx");
        employeeDto.setDateOfBirth(LocalDate.of(1990, 10, 10));
        employeeDto.setDateOfHire(LocalDate.of(2021, 10, 10));

        fail(String.valueOf(employeeService.addNewEmployee(employeeDto)));
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void testNotFoundEmployee() throws EmployeeNotFoundException {
        Assert.assertEquals(0, employeeService.findAll().size());
        fail(String.valueOf(employeeService.findById(2L)));
    }

    @Test
    public void testUpdateEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("xxx");
        employeeDto.setLastName("xxx");
        employeeDto.setDateOfBirth(LocalDate.of(1990, 10, 10));
        employeeService.addNewEmployee(employeeDto);

        Assert.assertEquals("xxx", employeeService.findAll().get(0).getFirstName());

        employeeDto.setFirstName("x");
        employeeService.updateEmployee(employeeDto, employeeService.findAll().get(0).getId());

        Assert.assertEquals("x", employeeService.findAll().get(0).getFirstName());
    }

    @Test
    public void testFindAllWorking() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("xxx");
        employeeDto.setLastName("xxx");
        employeeDto.setDateOfBirth(LocalDate.of(1990, 10, 10));
        employeeService.addNewEmployee(employeeDto);
        employeeDto.setEmployeeStatus(EmployeeStatus.ON_VACATION);
        employeeService.addNewEmployee(employeeDto);
        employeeDto.setEmployeeStatus(EmployeeStatus.RETIRED);
        employeeService.addNewEmployee(employeeDto);

        Assert.assertEquals(2,employeeService.findAllWorking().size());

        Assert.assertEquals(1,employeeService.findAllNotWorking().size());
    }
}
