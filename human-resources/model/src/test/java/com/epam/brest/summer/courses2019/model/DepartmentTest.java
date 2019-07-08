package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.Test;

public class DepartmentTest {

    Department department = new Department();

    @Test
    public void getDepartentId() {
        department.setDepartmentId(15);
        Assert.assertTrue(department.getDepartmentId().equals(15));
    }

    @Test
    public void getDepartentName() {
        department.setDepartmentName("IT");
        Assert.assertTrue(department.getDepartmentName().equals("IT"));
    }
}