package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
public class DepartmentServiceImplTest {

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
        int id = 1;
        Department department = departmentService.findById(id);

        assertNotNull(department);
        assertEquals("DEV", department.getDepartmentName());
    }

    @Test
    void update() {
        int id = 2;
        Department department = create();
        department.setDepartmentId(id);
        departmentService.update(department);
        department = departmentService.findById(id);

        assertNotNull(department);
        assertEquals("name", department.getDepartmentName());
    }

    @Test
    void delete() {
        int id = 3;
        departmentService.delete(id);
        assertThrows(RuntimeException.class, () -> departmentService.findById(id));
    }

    @Test
    void add() {
        long count = departmentService.findAll().size();
        assertThrows(DuplicateKeyException.class, () -> departmentService.add(create(), create()));
        long newCount = departmentService.findAll().size();
        assertEquals(count, newCount);
    }

    private Department create() {
        Department department = new Department();
        department.setDepartmentName("name");
        return department;
    }
}