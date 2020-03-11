package com.ex.employeesAPI.employee;

import com.ex.employeesAPI.employee.dto.EmployeeDto;
import com.ex.employeesAPI.employee.employeeStatus.EmployeeStatus;
import com.ex.employeesAPI.employee.model.Employee;
import com.ex.employeesAPI.employee.repository.EmployeeRepository;
import com.ex.employeesAPI.employee.service.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import static org.junit.Assert.fail;


@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("com.ex.employeesAPI.employee.service")
@RunWith(SpringRunner.class)
public class EmployeeEntityTest {
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testAddEmployee(){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("xxx");
        employeeDto.setLastName("xxx");
        employeeDto.setDateOfBirth(LocalDate.of(1990,10,10));
        employeeService.addNewEmployee(employeeDto);
        Assert.assertEquals(employeeService.findAll().size(),1);
    }
    @Test(expected = ConstraintViolationException.class)
    public void testAddEmployeeWithEmptyName() throws ConstraintViolationException{
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("");
        employeeDto.setLastName("xxx");
        employeeDto.setDateOfBirth(LocalDate.of(1990,10,10));
        fail(String.valueOf(employeeService.addNewEmployee(employeeDto)));
    }
}
