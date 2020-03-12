package com.ex.employeesAPI.payment;

import com.ex.employeesAPI.employee.dto.EmployeeDto;
import com.ex.employeesAPI.employee.service.EmployeeService;
import com.ex.employeesAPI.payment.dto.PaymentDto;
import com.ex.employeesAPI.payment.exception.DateStartIsAfterDayEndException;
import com.ex.employeesAPI.payment.exception.PaymentNotFoundException;
import com.ex.employeesAPI.payment.model.Payment;
import com.ex.employeesAPI.payment.service.PaymentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("com.ex.employeesAPI.payment.service")
@ComponentScan("com.ex.employeesAPI.employee.service")
@RunWith(SpringRunner.class)
public class PaymentTests {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private EmployeeService employeeService;

    @Before
    public void setUp() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("xxx");
        employeeDto.setLastName("xxx");
        employeeDto.setDateOfBirth(LocalDate.of(1990, 10, 10));
        employeeService.addNewEmployee(employeeDto);
        employeeDto.setDateOfHire(LocalDate.of(2010, 10, 10));
        employeeService.addNewEmployee(employeeDto);
    }

    @Test
    public void testAddNewPayment() {
        Long id = employeeService.findAll().get(0).getId();
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(2000.33);
        paymentService.addNewPayment(paymentDto, id);
        Long paymentId = paymentService.findAll().get(0).getId();
        // check that date of payment is today date
        Assert.assertEquals(LocalDate.now(), paymentService.findById(paymentId).getDateOfPayment());

        // check that payment has employee

        Assert.assertEquals(id.longValue(), paymentService.findById(paymentId).getEmployee().getId().longValue());

    }

    @Test
    public void testUpdatePayment() {
        Long id = employeeService.findAll().get(0).getId();
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(2000.33);
        paymentService.addNewPayment(paymentDto, id);
        Long paymentId = paymentService.findAll().get(0).getId();

        Assert.assertEquals("2000.33", paymentService.findById(paymentId).getAmount().toString());

        paymentDto.setAmount(200.33);
        paymentService.updatePayment(paymentDto, paymentId, id);

        Assert.assertEquals("200.33", paymentService.findById(paymentId).getAmount().toString());

    }

    @Test
    public void  testGetPaymentByEmployeeId(){
        Long id = employeeService.findAll().get(0).getId();
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(2000.33);
        paymentService.addNewPayment(paymentDto, id);
        paymentDto.setAmount(2000D);
        paymentService.addNewPayment(paymentDto,id);
        List<Payment> payments = paymentService.findAllByEmployee(id);

        Assert.assertEquals(2,payments.size());
        Assert.assertEquals("2000.33",payments.get(0).getAmount().toString());
        Assert.assertEquals("2000.0",payments.get(1).getAmount().toString());

    }
    @Test
    public void  testGetPaymentByFewEmployees(){
        Long id = employeeService.findAll().get(0).getId();
        Long id2 = employeeService.findAll().get(1).getId();
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(2000.33);
        paymentService.addNewPayment(paymentDto, id);
        paymentDto.setAmount(2000D);
        paymentService.addNewPayment(paymentDto,id);
        paymentService.addNewPayment(paymentDto,id2);
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        ids.add(id2);
        List<Payment> payments = paymentService.findAllByEmployees(ids);

        Assert.assertEquals(3,payments.size());
        Assert.assertEquals("2000.33",payments.get(0).getAmount().toString());
        Assert.assertEquals("2000.0",payments.get(1).getAmount().toString());
        Assert.assertEquals("2000.0",payments.get(2).getAmount().toString());

    }
    @Test
    public void testCountPaymentForLastYear(){
        Long id = employeeService.findAll().get(0).getId();
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(2000D);
        paymentService.addNewPayment(paymentDto, id);
        paymentDto.setAmount(1000D);
        paymentService.addNewPayment(paymentDto,id);
        paymentDto.setAmount(3000D);
        paymentDto.setDateOfPayment(LocalDate.of(2020,1,1));
        paymentService.addNewPayment(paymentDto,id);
        paymentDto.setAmount(10000D);
        paymentDto.setDateOfPayment(LocalDate.of(2019,1,1));
        paymentService.addNewPayment(paymentDto,id);
        double result =  paymentService.countPaymentAmountFromLastYear(id);
        Assert.assertEquals(2000D,result,2);
    }

    @Test
    public void testCountPaymentInPeriod() throws DateStartIsAfterDayEndException {
        Long id = employeeService.findAll().get(0).getId();
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(2000D);
        paymentDto.setDateOfPayment(LocalDate.of(2010,1,1));
        paymentService.addNewPayment(paymentDto, id);
        paymentDto.setAmount(1000D);
        paymentDto.setDateOfPayment(LocalDate.of(2010,3,1));
        paymentService.addNewPayment(paymentDto,id);
        paymentDto.setAmount(3000D);
        paymentDto.setDateOfPayment(LocalDate.of(2010,12,31));
        paymentService.addNewPayment(paymentDto,id);
        paymentDto.setAmount(10000D);
        paymentDto.setDateOfPayment(LocalDate.of(2011,1,1));
        paymentService.addNewPayment(paymentDto,id);
        double result = paymentService.countPaymentAmountFromPeriod(LocalDate.of(2010,1,1),LocalDate.of(2010,12,31),id);
        Assert.assertEquals(2000D,result,2);
    }

    @Test(expected = DateStartIsAfterDayEndException.class)
    public void testBadDataInputInCountPeriod() throws  DateStartIsAfterDayEndException{
        Long id = employeeService.findAll().get(0).getId();
        fail(String.valueOf(paymentService.countPaymentAmountFromPeriod(LocalDate.now(), LocalDate.now().minusWeeks(1),id)));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testBadAmountInput() throws  ConstraintViolationException{
        Long id = employeeService.findAll().get(0).getId();
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(200.333);
        fail(String.valueOf(paymentService.addNewPayment(paymentDto,id)));
    }
    @Test(expected = PaymentNotFoundException.class)
    public void testPaymentNotFOund() throws  PaymentNotFoundException{
        fail(String.valueOf(paymentService.findById(2L)));
    }
}
