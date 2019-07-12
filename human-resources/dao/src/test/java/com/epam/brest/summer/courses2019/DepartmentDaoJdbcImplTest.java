package com.epam.brest.summer.courses2019;

import com.epam.brest.summer.courses2019.model.Department;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DepartmentDaoJdbcImplTest {

    DepartmentDao departmentDao = new DepartmentDaoJdbcImpl();

    @Test
    void findAll() {
        List<Department> departments = departmentDao.findAll();
        Assert.assertNotNull(departments);
        Assert.assertTrue(departments.size() > 0);
    }

}
