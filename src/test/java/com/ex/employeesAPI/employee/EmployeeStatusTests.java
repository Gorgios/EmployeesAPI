package com.ex.employeesAPI.employee;

import com.ex.employeesAPI.employee.employee.EmployeeStatus;
import com.ex.employeesAPI.employee.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class EmployeeStatusTests {


    @Test
    public void testCheckIsWorking() {
        List<EmployeeStatus> employed = new ArrayList<>();
        employed.add(EmployeeStatus.SICK_LEAVE);
        employed.add(EmployeeStatus.WORKING);
        employed.add(EmployeeStatus.ON_VACATION);
        List <EmployeeStatus> unemployed = new ArrayList<>();
        unemployed.add(EmployeeStatus.FIRED);
        unemployed.add(EmployeeStatus.RETIRED);
        for (EmployeeStatus e: employed)
            Assert.assertTrue(e.isWorking());
        for (EmployeeStatus e : unemployed)
            Assert.assertFalse(e.isWorking());
    }

    @Test
    public void testStatusReturnGoodString() {
        Employee employee = new Employee();
        employee.setEmployeeStatus(EmployeeStatus.ON_VACATION);
        Assert.assertEquals("On vacation", employee.getEmployeeStatus().toString());
    }
}
