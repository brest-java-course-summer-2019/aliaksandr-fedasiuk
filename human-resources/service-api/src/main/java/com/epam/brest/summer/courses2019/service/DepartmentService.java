package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Department;
import java.util.List;

/**
 * Department Service Interface.
 */
public interface DepartmentService {

    /**
     * Find all departments stream.
     *
     * @return departments .
     */
    List<Department> findAll();

    /**
     * Get all departments with avg salary by department.
     *
     * @return departments list.
     */
    List<Department> findAllWithAvgSalary();

    /**
     * Find Department By Id.
     *
     * @param id id
     * @return Department
     */
    Department findById(Integer id);

    /**
     * Update department.
     *
     * @param department department
     */
    void update(Department department);

    /**
     * Delete Department.
     *
     * @param id department id
     */
    void delete(int id);

    void add(Department... departments);
}
