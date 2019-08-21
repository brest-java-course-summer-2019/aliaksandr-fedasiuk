package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class DepartmentTest {

    Department department = new Department();

    @Test
    public void getDepartmentId() {
        department.setDepartmentId(15);
        Assert.assertTrue(department.getDepartmentId().equals(15));
    }

    @Test
    public void getDepartmentName() {
        department.setDepartmentName("IT");
        Assert.assertTrue(department.getDepartmentName().equals("IT"));
    }

}