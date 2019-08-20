package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Employee;

import java.util.List;
import java.util.Optional;

/**
 * Employee DAO Interface.
 */
public interface EmployeeDao {

    /**
     * Get all employees.
     *
     * @return list of all employees
     */
    List<Employee> findAll();

    /**
     * Get all employees with specified department id.
     *
     * @param departmentId department id
     * @return list of employees by department id
     */
    List<Employee> findByDepartmentId(Integer departmentId);

    /**
     * Get employee with specified id.
     *
     * @param employeeId employee id
     * @return employee by id
     */
    Optional<Employee> findById(Integer employeeId);

    /**
     * Persist new employee.
     *
     * @param employee employee
     * @return employee
     */
    Employee add(Employee employee);

    /**
     * Update employee.
     *
     * @param employee employee
     */
    void update(Employee employee);

    /**
     * Delete employee with specified id.
     *
     * @param employeeId department id
     */
    void delete(Integer employeeId);

}
