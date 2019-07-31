package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Transactional
@Rollback
public class EmployeeDaoJdbcImplTest {

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void findAll() {
        List<Employee> employees = employeeDao.findAll();
        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }

    @Test
    public void findByDepartmentId() {
        List<Employee> employees = employeeDao.findByDepartmentId(1);
        assertNotNull(employeeDao);
        assertTrue(employees.size() > 0);
    }

    @Test
    public void findById() {
        assertNotNull(employeeDao);
        Employee employee = employeeDao.findById(1).get();
        assertTrue(employee.getEmployeeId().equals(1));
        assertTrue(employee.getFirstName().equals("FUSER10"));
        assertTrue(employee.getLastName().equals("LUSER10"));
        assertTrue(employee.getEmail().equals("email10@mail.com"));
        assertEquals(new BigDecimal("100"), employee.getSalary());
        assertTrue(employee.getDepartmentId().equals(1));
    }

    @Test
    public void add() {
        List<Employee> employees = employeeDao.findAll();
        int sizeBefore = employees.size();
        Employee employee = new Employee("email2", "firstName2", "lastName2", new BigDecimal("302"), 1);
        Employee newEmployee = employeeDao.add(employee);
        assertNotNull(newEmployee.getEmployeeId());
        assertTrue(newEmployee.getFirstName().equals(employee.getFirstName()));
        assertTrue(newEmployee.getLastName().equals(employee.getLastName()));
        assertTrue(newEmployee.getEmail().equals(employee.getEmail()));
        assertTrue(newEmployee.getSalary().equals(employee.getSalary()));
        assertTrue(newEmployee.getDepartmentId().equals(employee.getDepartmentId()));
        assertTrue((sizeBefore + 1) == employeeDao.findAll().size());
    }

    @Test
    public void update() {
        Employee employee = employeeDao.findById(1).get();
        employee.setFirstName("newFirstName");
        employee.setLastName("newLastName");
        employee.setSalary(new BigDecimal("600"));
        employeeDao.update(employee);
        Employee updatedEmployee = employeeDao.findById(employee.getEmployeeId()).get();
        assertTrue(updatedEmployee.getEmployeeId().equals(employee.getEmployeeId()));
        assertTrue(updatedEmployee.getFirstName().equals(employee.getFirstName()));
        assertTrue(updatedEmployee.getLastName().equals(employee.getLastName()));
        assertTrue(updatedEmployee.getSalary().equals(employee.getSalary()));
        assertTrue(updatedEmployee.getDepartmentId().equals(employee.getDepartmentId()));
    }

    @Test
    public void delete() {
        Employee employee = new Employee("email2", "firstName2", "lastName2", new BigDecimal("302"), 1);
        employeeDao.add(employee);
        List<Employee> employees = employeeDao.findAll();
        int sizeBefore = employees.size();
        employeeDao.delete(employee.getEmployeeId());
        assertTrue((sizeBefore - 1) == employeeDao.findAll().size());
    }

}
