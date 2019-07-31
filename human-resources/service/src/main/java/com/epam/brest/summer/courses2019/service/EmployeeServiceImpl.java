package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.EmployeeDao;
import com.epam.brest.summer.courses2019.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * Employee Service Interface implementation.
 */
@Component
@Transactional
public class EmployeeServiceImpl implements EmployeeService {


    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() {
        LOGGER.debug("Find all employees");
        return employeeDao.findAll();
    }

    @Override
    public List<Employee> findByDepartmentId(Integer departmentId) {
        LOGGER.debug("findByDepartmentId({})", departmentId);
        return employeeDao.findByDepartmentId(departmentId);
    }

    @Override
    public Optional<Employee> findById(Integer employeeId) {
        LOGGER.debug("findById({})", employeeId);
        return Optional.of(employeeDao.findById(employeeId).orElseThrow(()
                -> new RuntimeException("Failed to get employee from DB")));
    }

    @Override
    public Employee add(Employee employee) {
        LOGGER.debug("add({})", employee);
        return employeeDao.add(employee);
    }

    @Override
    public void update(Employee employee) {
        LOGGER.debug("update({})", employee);
        employeeDao.update(employee);
    }

    @Override
    public void delete(Integer employeeId) {
        LOGGER.debug("delete({})", employeeId);
        employeeDao.delete(employeeId);
    }
}
