package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
@Transactional
@Rollback
public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void findAll() {
        List<Employee> employees = employeeService.findAll();

        assertNotNull(employees);
        assertFalse(employees.isEmpty());
    }

    @Test
    void findById() {
        int id = 1;
        Employee employee = employeeService.findById(id);

        assertNotNull(employee);
        assertEquals("FUSER10", employee.getFirstName());
    }

    @Test
    void update() {
        int id = 2;
        Employee employee = create();
        employee.setEmployeeId(id);
        employeeService.update(employee);
        employee = employeeService.findById(id);

        assertNotNull(employee);
        assertEquals("name", employee.getFirstName());
    }

    @Test
    void delete() {
        int id = 3;
        employeeService.delete(id);
        assertThrows(RuntimeException.class, () -> employeeService.findById(id));
    }

    private Employee create() {
        Employee employee = new Employee();
        employee.setDepartmentId(1);
        employee.setFirstName("name");
        return employee;
    }
}
