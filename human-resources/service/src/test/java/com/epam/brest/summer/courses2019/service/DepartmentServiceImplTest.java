package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    void findAll() {
        List<Department> departments = departmentService.findAll();

        assertNotNull(departments);
        assertFalse(departments.isEmpty());
    }

    @Test
    void findById() {
        Department department = departmentService.findById(1);

        assertNotNull(department);
        assertEquals("DEV", department.getDepartmentName());
    }
}