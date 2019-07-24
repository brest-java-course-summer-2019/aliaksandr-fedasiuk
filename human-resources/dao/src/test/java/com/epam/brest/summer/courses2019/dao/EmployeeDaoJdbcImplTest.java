package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
public class EmployeeDaoJdbcImplTest {

    @Autowired
    EmployeeDao employeeDao;

    private Employee employee;

    @Before
    public void changes() {
        employee = new Employee("email1", "firstName1", "lastName1", new BigDecimal("301"), 1);
        employee = employeeDao.add(employee);
    }

    @After
    public void cleanChanges() {
        employeeDao.delete(employee.getEmployeeId());
    }

    @Test
    public void findAll() {
        List<Employee> employees = employeeDao.findAll();
        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }

    @Test
    public void findByDepartmentId() throws Exception {
        List<Employee> employees = employeeDao.findByDepartmentId(1);
        assertNotNull(employeeDao);
        assertTrue(employees.size() > 0);
    }

    @Test
    public void findById() throws Exception {
        Employee newEmployee = employeeDao.findById(employee.getEmployeeId()).get();
        assertNotNull(employeeDao);
        assertTrue(newEmployee.getEmployeeId().equals(employee.getEmployeeId()));
        assertTrue(newEmployee.getFirstName().equals("firstName1"));
        assertTrue(newEmployee.getLastName().equals("lastName1"));
        assertTrue(newEmployee.getEmail().equals("email1"));
        assertTrue(newEmployee.getSalary().equals(new BigDecimal("301")));
        assertTrue(newEmployee.getDepartmentId().equals(1));
    }

    @Test
    public void add() throws Exception {
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
    public void update() throws Exception {
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
    public void delete() throws Exception {
        Employee employee = new Employee("email2", "firstName2", "lastName2", new BigDecimal("302"), 1);
        employeeDao.add(employee);
        List<Employee> employees = employeeDao.findAll();
        int sizeBefore = employees.size();
        employeeDao.delete(employee.getEmployeeId());
        assertTrue((sizeBefore - 1) == employeeDao.findAll().size());
    }

}
