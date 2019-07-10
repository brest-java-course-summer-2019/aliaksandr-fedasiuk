package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class EmployeeTest {

    Employee employee = new Employee();

    @Test
    public void getEmployeeId() {
        employee.setEmployeeId(15);
        Assert.assertTrue(employee.getEmployeeId().equals(15));
    }

    @Test
    public void getDepartmentId() {
        employee.setDepartmentId(16);
        Assert.assertTrue(employee.getDepartmentId().equals(16));
    }

    @Test
    public void getFirstName() {
        employee.setFirstName("FirstName");
        Assert.assertTrue(employee.getFirstName().equals("FirstName"));
    }

    @Test
    public void getLastName() {
        employee.setLastName("LastName");
        Assert.assertTrue(employee.getLastName().equals("LastName"));
    }

    @Test
    public void getEmail() {
        employee.setEmail("Email");
        Assert.assertTrue(employee.getEmail().equals("Email"));
    }

    @Test
    public void getSalary() {
        employee.setSalary(new BigDecimal("12.12"));
        Assert.assertTrue(employee.getSalary().compareTo(new BigDecimal("12.12")) == 0);
    }


}
