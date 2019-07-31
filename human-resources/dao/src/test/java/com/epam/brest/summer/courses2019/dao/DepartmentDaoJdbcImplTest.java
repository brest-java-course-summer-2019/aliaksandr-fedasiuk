package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Transactional
@Rollback
public class DepartmentDaoJdbcImplTest {

    private static final String DEVELOPMENT = "DEV";
    private static final String EDUCATION = "Education";
    private static final String NEW_EDUCATION = "NEWEducation";

    @Autowired
    DepartmentDao departmentDao;

    @Test
    public void findAll() {
        List<Department> departments = departmentDao.findAll();
        assertNotNull(departments);
        assertTrue(departments.size() > 0);
    }

    @Test
    public void findAllWithAvgSalary() {
        List<Department> departments = departmentDao.findAllWithAvgSalary();
        assertNotNull(departments);
        assertTrue(departments.size() > 0);
        assertTrue(departments.get(0).getAvgSalary().intValue() > 0);
    }

    @Test
    public void getDepartmentById() {
        Department department = departmentDao.findById(1).get();
        assertNotNull(department);
        assertTrue(department.getDepartmentId().equals(1));
        assertEquals(DEVELOPMENT, department.getDepartmentName());
    }

    @Test
    public void addDepartment() {
        Department testDepartment = new Department();
        testDepartment.setDepartmentName("QA");
        Department newDepartment = departmentDao.add(testDepartment);
        assertNotNull(newDepartment.getDepartmentId());
    }

    @Test
    public void updateDepartment() {
        Department newDepartment = new Department(EDUCATION);
        newDepartment = departmentDao.add(newDepartment);
        newDepartment.setDepartmentName(NEW_EDUCATION);
        departmentDao.update(newDepartment);
        Department updatedDepartment = departmentDao.findById(newDepartment.getDepartmentId()).get();
        assertTrue(newDepartment.getDepartmentId().equals(updatedDepartment.getDepartmentId()));
        assertTrue(newDepartment.getDepartmentName().equals(updatedDepartment.getDepartmentName()));
    }

    @Test
    public void deleteDepartment() {
        Department department = new Department(EDUCATION);
        department = departmentDao.add(department);
        List<Department> departments = departmentDao.findAll();
        int sizeBefore = departments.size();
        departmentDao.delete(department.getDepartmentId());
        assertTrue((sizeBefore - 1) == departmentDao.findAll().size());
    }

}
